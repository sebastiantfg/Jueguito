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
public class BlockBullet {
    int x, y;
    int width = 5, height = 10;
    int speed = 5;

    public BlockBullet(int x, int y) {
        this.x = x;
        this.y = y;
    }

    // Método para mover el disparo hacia abajo
    public void move() {
        y += speed;
    }

    // Método para dibujar el disparo
    public void draw(Graphics g) {
        g.setColor(Color.GREEN);
        g.fillRect(x, y, width, height);
    }

    // Obtener el área de colisión para el disparo
    public Rectangle getBounds() {
        return new Rectangle(x, y, width, height);
    }
}
