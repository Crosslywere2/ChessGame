package com.chess;

import com.crossly.GameContainer;
import com.crossly.GameManager;

public class Chess extends GameManager {

    private static int width = 160;
    private static int height = 144;
    private static int scale = 4;

    public Chess(GameContainer gc) {
        super(gc);
    }

    public void onUpdate(double d) {
    }

    public void onRender() {
        drawBoard(16, 8, 128, 128, 16, 0xffff0000, 0xff0080ff);
    }

    public static void main(String[] args) {
        new Chess(new GameContainer("Chess", width, height, scale)).play();
    }

    private void drawBoard(int posX, int posY, int width, int height, int size, int color1, int color2) {
        boolean swap = true;
        for (int y = 0; y < height; y += size) {
            swap = !swap;
            for (int x = 0; x < width; x += size) {
                swap = !swap;
                renderer.fillRectangle(x + posX, y + posY, size, size, swap ? color1 : color2);
            }
        }
    }
}
