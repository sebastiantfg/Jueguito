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
public class Alien {
    int x, y, width = 40, height = 40;

    public Alien(int x, int y) {
        this.x = x;
        this.y = y;
    }

    // Método para dibujar el alien
    public void draw(Graphics g) {
        g.setColor(Color.GREEN); // Color de los aliens
        g.fillRect(x, y, width, height);
    }

    // Método para obtener el área del alien (para detectar colisiones)
    public Rectangle getBounds() {
        return new Rectangle(x, y, width, height);
    }
}