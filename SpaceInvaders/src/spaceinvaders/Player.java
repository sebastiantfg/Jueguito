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
public class Player {
    int x, y;
    int width = 50, height = 50;
    int speed = 5;

    public Player(int x, int y) {
        this.x = x;
        this.y = y;
    }

    // Método para mover al jugador
    public void move(int dx) {
        x += dx;
    }

    // Método para dibujar al jugador
    public void draw(Graphics g) {
        g.setColor(Color.BLUE); // Color del jugador
        g.fillRect(x, y, width, height); // Dibuja el jugador como un rectángulo
    }

    // Método para obtener el área de colisión del jugador
    public Rectangle getBounds() {
        return new Rectangle(x, y, width, height); // Devuelve un rectángulo con las coordenadas y tamaño del jugador
    }
}