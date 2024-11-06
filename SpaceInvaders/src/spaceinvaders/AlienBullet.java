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
public class AlienBullet {
    int x, y, width = 5, height = 10;

    // Constructor
    public AlienBullet(int x, int y) {
        this.x = x;
        this.y = y;
    }

    // Método para mover el disparo hacia abajo
    public void move() {
        y += 5; // Los disparos se mueven hacia abajo
    }

    // Método para dibujar el disparo
    public void draw(Graphics g) {
        g.setColor(Color.YELLOW); // Color del disparo
        g.fillRect(x, y, width, height); // Dibuja el disparo
    }

    // Método que devuelve el área del disparo (utilizado para detectar colisiones)
    public Rectangle getBounds() {
        return new Rectangle(x, y, width, height);
    }
}