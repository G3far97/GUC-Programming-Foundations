package controller;

import view.view;

public class GUI {
private static final long serialVersionUID = 1L;
private view GameView;

GUI(){
	GameView = new view();
	GameView.setVisible(true);
}
public static void main(String[] args) {
	new GUI();
	}
}