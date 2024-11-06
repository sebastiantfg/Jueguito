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
public class Bullet {
    int x, y, width = 5, height = 10;

    public Bullet(int x, int y) {
        this.x = x;
        this.y = y;
    }

    // Método para mover la bala (hacia arriba)
    public void move() {
        y -= 5; // La bala se mueve hacia arriba
    }

    // Método para dibujar la bala
    public void draw(Graphics g) {
        g.setColor(Color.RED); // Color de la bala
        g.fillRect(x, y, width, height); // Dibuja el rectángulo de la bala
    }

    // Método que devuelve el área de la bala (usado para la colisión)
    public Rectangle getBounds() {
        return new Rectangle(x, y, width, height); // Retorna un rectángulo para la colisión
    }
}