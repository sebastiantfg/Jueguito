/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package spaceinvaders;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.Timer;

/**
 *
 * @author PROPIETARIO
 */
public class GamePanel extends javax.swing.JPanel {

    private Timer timer;  // Timer para actualizar el juego
    private Player player;
    private Alien[] aliens; // Array de aliens (bloques verdes)
    private Bullet bullet;   // Bala del jugador
    private ArrayList<Block> blocks; // Escudos (bloques)
    private ArrayList<AlienBullet> alienBullets; // Disparos de los aliens
    private int lives = 3;  // Vidas del jugador
    private boolean gameOver = false; // Bandera para indicar si el juego terminó

    public GamePanel() {
        // Configura el tamaño del panel
        setPreferredSize(new Dimension(800, 600));

        // Inicializa al jugador y los aliens
        player = new Player(375, 500); // Establece la posición inicial del jugador
        aliens = new Alien[10]; // Array de 10 aliens (bloques verdes)
        for (int i = 0; i < aliens.length; i++) {
            aliens[i] = new Alien(50 + i * 70, 50); // Posiciones iniciales de los aliens
        }

        // Inicializa los bloques (escudos) en una fila horizontal
        blocks = new ArrayList<>();
        int shieldY = 400;  // Posición vertical de los escudos
        for (int i = 0; i < 11; i++) {
            blocks.add(new Block(70 + i * 60, shieldY)); // Los bloques estarán en una fila
        }

        // Inicializa la lista de disparos de los aliens
        alienBullets = new ArrayList<>();

        // Crea el temporizador que se actualiza cada 10ms
        timer = new Timer(10, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (bullet != null) {
                    bullet.move(); // Mueve la bala del jugador hacia arriba
                    if (bullet.y < 0) {
                        bullet = null; // Elimina la bala si sale de la pantalla
                    } else {
                        // Verificar colisiones con los aliens (bloques verdes)
                        for (int i = 0; i < aliens.length; i++) {
                            if (aliens[i] != null && bullet != null && bullet.getBounds().intersects(aliens[i].getBounds())) {
                                // Colisión detectada
                                aliens[i] = null;  // Elimina el alien
                                bullet = null;     // Elimina la bala
                            }
                        }
                    }
                }

                // Mover los disparos de los aliens
                ArrayList<AlienBullet> bulletsToRemove = new ArrayList<>();
                for (AlienBullet alienBullet : alienBullets) {
                    alienBullet.move(); // Mueve el disparo de los aliens
                    if (alienBullet.y > getHeight()) {
                        bulletsToRemove.add(alienBullet); // Elimina el disparo si sale de la pantalla
                    } else {
                        // Comprobar colisiones con los bloques (escudos)
                        for (Block block : blocks) {
                            if (block.getBounds().intersects(alienBullet.getBounds())) {
                                blocks.remove(block); // Elimina el bloque de escudo si es golpeado
                                bulletsToRemove.add(alienBullet); // Elimina el disparo
                                break;
                            }
                        }

                        // Comprobar colisiones con el jugador
                        if (player.getBounds().intersects(alienBullet.getBounds())) {
                            lives--;  // El jugador pierde una vida
                            bulletsToRemove.add(alienBullet); // Elimina el disparo que tocó al jugador
                            if (lives <= 0) {
                                // Si las vidas llegan a cero, el juego termina
                                gameOver = true;
                                JOptionPane.showMessageDialog(null, "Game Over!");
                                System.exit(0); // Termina el juego
                            }
                        }
                    }
                }
                alienBullets.removeAll(bulletsToRemove); // Elimina los disparos que deben ser eliminados

                // Llamar al método para que los aliens disparen
                shootAlienBullets();

                // Verificar si el jugador ha ganado (cuando todos los aliens son eliminados)
                boolean allAliensDestroyed = true;
                for (Alien alien : aliens) {
                    if (alien != null) {
                        allAliensDestroyed = false;
                        break;
                    }
                }
                if (allAliensDestroyed && !gameOver) {
                    // Mostrar mensaje de victoria
                    JOptionPane.showMessageDialog(null, "Victoria GG EZ!");
                    gameOver = true;
                }

                repaint(); // Redibuja el panel
            }
        });
        timer.start();

        // Habilita el panel para escuchar eventos del teclado
        setFocusable(true);
        addKeyListener(new KeyListener() {
            public void keyTyped(KeyEvent e) {}

            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_LEFT) {
                    player.move(-5); // Mueve el jugador hacia la izquierda
                } else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
                    player.move(5); // Mueve el jugador hacia la derecha
                } else if (e.getKeyCode() == KeyEvent.VK_SPACE) {
                    if (bullet == null) {
                        bullet = new Bullet(player.x + player.width / 2 - 2, player.y); // Dispara una bala
                    }
                }
            }

            public void keyReleased(KeyEvent e) {}
        });
    }

    // Método para dibujar los componentes (jugador, aliens, bloques y bala)
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.BLACK); // Fondo negro
        g.fillRect(0, 0, getWidth(), getHeight()); // Dibuja el fondo

        player.draw(g); // Dibuja al jugador
        for (Alien alien : aliens) {
            if (alien != null) {
                alien.draw(g); // Dibuja cada alien (bloques verdes)
            }
        }
        if (bullet != null) {
            bullet.draw(g); // Dibuja la bala si está presente
        }
        for (Block block : blocks) {
            block.draw(g); // Dibuja los bloques de escudo
        }
        for (AlienBullet alienBullet : alienBullets) {
            alienBullet.draw(g); // Dibuja los disparos de los aliens
        }

        // Mostrar las vidas restantes
        g.setColor(Color.WHITE);
        g.drawString("Lives: " + lives, 10, 10);
    }

    // Método para que los aliens (bloques verdes) disparen (se llama en cada ciclo de actualización)
    public void shootAlienBullets() {
        for (Alien alien : aliens) {
            if (alien != null && Math.random() < 0.01) { // Probabilidad de disparo
                alienBullets.add(new AlienBullet(alien.x + alien.width / 2 - 2, alien.y + alien.height));
            }
        }
    }
}
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
