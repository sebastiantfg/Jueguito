/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package spaceinvaders;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

/**
 *
 * @author PROPIETARIO
 */
public class Shield {
    private int x, y;
    private final int width = 50, height = 30; // Tamaño del bloque

    public Shield(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void draw(Graphics g) {
        g.setColor(Color.GREEN);  // Color del escudo
        g.fillRect(x, y, width, height); // Dibuja el rectángulo (escudo)
    }

    // Métodos para mover, destruir, etc.
    public Rectangle getBounds() {
        return new Rectangle(x, y, width, height);
    }
}