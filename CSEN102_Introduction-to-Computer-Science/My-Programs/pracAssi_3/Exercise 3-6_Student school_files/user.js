// example of using the brython debugger without initializing brython in body
(function() {
    window.brython(1);

    var $B = window.__BRYTHON__;
    var _b_ = $B.builtins;
    var $io = {
        __class__: $B.$type,
        __name__: 'io'
    };
    $io.__mro__ = [$io, _b_.object.$dict];

    var doc = function(d) {
        return document.getElementById(d);
    };

    var editor = window.CodeMirror.fromTextArea(doc("editor"), {
        autofocus: true,
        lineNumbers: true,
        indentUnits: 4,
        lineWrapping: true,
        gutters: ["cm-trace-marker", "CodeMirror-lint-markers", "CodeMirror-linenumbers"],
        lint: {},
        styleActiveLine: true,
        mode: {
            name: "python",
            version: 3,
            singleLineStringErrors: false
        }
    });

    editor.on('change', function(cm) {
        var text = cm.getValue();
        if (text.charAt(text.length - 1) !== "\n") {
            var cursor = editor.getDoc().getCursor();
            cm.setValue(text + "\n");
            editor.getDoc().setCursor(cursor, {
                scroll: true
            });
        }
    })

    editor.setOption("extraKeys", {
        Tab: function(cm) {
            var spaces = Array(cm.getOption("indentUnit") + 1).join(" ");
            cm.replaceSelection(spaces);
        }
    });

    function setDebugLinePointer(n) {
        clearPointer();
        editor.setGutterMarker(n-1, "cm-trace-marker", makeMarker());
    };

    function clearPointer () {
        editor.clearGutter("cm-trace-marker");
    }

    function makeMarker() {
        var marker = document.createElement("div");
        marker.className = "cm-debug-arrow";
        marker.innerHTML = "➤";
        return marker;
    }


    var output;
    var storage = localStorage;


    function reset_src() {
        if (storage && storage['py_src']) {
            editor.setValue(storage["py_src"]);
        } else {
            editor.setValue('for i in range(10):\n\tprint(i)');
        }
        gotoLine(0);
    }

    function gotoLine(line) {
        editor.getDoc().setCursor(line - 1, {
            scroll: true
        });
        setDebugLinePointer(line);
    }

    function clearLint() {
        editor.updateLinting([]);
    }

    reset_src();

    var Debugger = window.Brython_Debugger;
    var cout = {
        __class__: $io,
        write: function(data) {
            doc("console").value += data;
            return _b_.None;
        },
        flush: function() {}
    };
    $B.stdout = $B.modules._sys.stdin = cout;
    $B.stderr = $B.modules._sys.stdin = cout;
    $B.stdin = $B.modules._sys.stdin = {
        __class__: $io,
        __original__: true,
        closed: false,
        len: 1,
        pos: 0,
        read: function() {
            return prompt();
        },
        readline: function() {
            return prompt();
        }
    };
    _b_.input = function input(arg) {
        var val;
        var stdin = ($B.imported.sys && $B.imported.sys.stdin || $B.stdin);
        // $B.stdout.write(arg);
        if (stdin.__original__) {
            val = prompt(arg)
            return (val ? val : "");
        }
        val = _b_.getattr(stdin, 'readline')();
        val = val.split('\n')[0];
        if (stdin.len === stdin.pos) {
            _b_.getattr(stdin, 'close')();
        }
        // $B.stdout.write(val+'\n');
        return (val);
    };

    function run() {
        doc("console").value = '';
        clearLint();
        var src = editor.getValue();
        if (storage) {
            storage["py_src"] = src;
        }

        doc('run').disabled = true
        Debugger.unset_events();
        var hist = Debugger.run_to_end(src);
        Debugger.reset_events();
        doc('run').disabled = false;
        if(hist.error) {
            var state = hist.errorState;
            doc('console').value = state.data;
            state.message = state.name+": "+state.message;
            state.severity = 'error';
            editor.updateLinting(CodeMirror.lintResult([state]));
        }
        PD.sendActivity({
            action: 'run',
            meta: {
                error: hist.error
            }
        });
    }

    function start_debugger(ev) {
        doc("console").value = '';
        clearLint();
        var src = editor.getValue();
        if (storage) {
            storage["py_src"] = src;
        }

        var hist = Debugger.start_debugger(src, true);
        PD.sendActivity({
            action: 'debug',
            meta: {
                error: hist.error
            }
        });
    }

    function stop_debugger(ev) {
        Debugger.stop_debugger();
    }

    function step_debugger(ev) {
        if (!Debugger.is_debugging()) {
            start_debugger();
        } else {
            Debugger.step_debugger()
        }
    }

    function step_back_debugger(ev) {
        Debugger.step_back_debugger()
    }

    function debug_started() {
        doc('run').disabled = true
        doc('debug').disabled = true
        doc('step').disabled = false
        doc('stop').disabled = false
        if (Debugger.is_recorded()) {
            if (Debugger.get_recorded_states().length > 0) {
                gotoLine(Debugger.get_recorded_states()[0].next_line_no);
            } else {
                Debugger.stop_debugger();
            }
        } else {
            Debugger.step_debugger()
        }
    }

    function debug_stoped() {
        doc('debug').disabled = false;
        doc('run').disabled = false;
        doc('step').disabled = true;
        doc('back').disabled = true;
        doc('stop').disabled = true;
        clearPointer();
    }

    function debug_step(state) {

        doc("console").value = "" + state.stdout;

        gotoLine(state.next_line_no);

        if (Debugger.is_last_step()) {
            doc('step').disabled = true;
        } else {
            doc('step').disabled = false;
        }
        if (Debugger.is_first_step()) {
            doc('back').disabled = true;
        } else {
            doc('back').disabled = false;
        }

        if(state.err) {
            doc('console').value = state.data;
            state.message = state.name+": "+state.message;
            state.severity = 'error';
            editor.updateLinting(CodeMirror.lintResult([state]));
        } else {
            clearLint()
        }
    }


    function debug_error(err, Debugger) {
        // if (Debugger.get_recorded_states().length === 0) {
        //     doc('console').value = err.data;
        //     Debugger.stop_debugger();
        // }
        // err.severity = 'error';
        
        // editor.updateLinting(CodeMirror.lintResult([err]));
    }

    function show_js(ev) {
        var src = editor.getValue();
        doc("console").value = $B.py2js(src, '__main__', '__main__', '__builtins__').to_js();
    }
    window.show_js = show_js;


    Debugger.on_debugging_started(debug_started);
    Debugger.on_debugging_end(debug_stoped);
    Debugger.on_debugging_error(debug_error);
    Debugger.on_step_update(debug_step);


    doc('run').addEventListener('click', run);
    doc('debug').addEventListener('click', start_debugger);
    doc('step').addEventListener('click', step_debugger);
    doc('back').addEventListener('click', step_back_debugger);
    doc('stop').addEventListener('click', stop_debugger);
})();
