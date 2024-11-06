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
public class Block {
    int x, y, width = 50, height = 20;

    // Constructor
    public Block(int x, int y) {
        this.x = x;
        this.y = y;
    }

    // Método para dibujar el bloque
    public void draw(Graphics g) {
        g.setColor(Color.BLUE); // Color de los bloques
        g.fillRect(x, y, width, height); // Dibuja el bloque
    }

    // Método que devuelve el área del bloque
    public Rectangle getBounds() {
        return new Rectangle(x, y, width, height);
    }
    
}
