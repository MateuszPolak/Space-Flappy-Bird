import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Random;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.Timer;
 
public class Flappy implements ActionListener, MouseListener,KeyListener{
    public static Flappy flappyBird;
    public final int WIDTH = 1200, HEIGHT = 800;
    public Refresh refresh;
    public ArrayList<Rectangle> columns;
    public boolean GameOver=false;
    public boolean mars, moon, earth, jupiter;
    public boolean easy, medium, hard;
    public static boolean started=false;
    public Random rand;
    public Rectangle bird;
    public int score;
    public int firstScore, secondScore, thirdScore;
    public String firstName, secondName, thirdName;
    public double gmars = 3;
    public double gmoon = 2;
    public double gearth = 3.3;
    public double gjupiter = 3.7;
    public int seasy = 5;
    public int smedium = 7;
    public int shard = 9;
    public int lives = 3;
    Image backgroundImage, columnImage, birdImage, birdredImage, livesImage;
    BufferedImage birdBufferredImage;
    
    public Flappy() {
        final JFrame frame = new JFrame();
        Timer gametimer = new Timer(20, this);
        Timer createColumnsTimerMedium = new Timer(3200, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	if(started == true && medium == true){
                addColumn(true);
            	}
            	else{
            	addColumn(false);
            	}
            }
        });
        Timer createColumnsTimerHard = new Timer(1800, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	if(started == true && hard == true){
                addColumn(true);
            	}
            	else{
            	addColumn(false);
            	}
            }
        });
        Timer createColumnsTimerEasy = new Timer(4700, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	if(started == true && easy == true){
                addColumn(true);
            	}
            	else{
            	addColumn(false);
            	}
            }
        });
        refresh = new Refresh();
        rand = new Random();
        frame.add(refresh);
        frame.setTitle("Space Flappy Bird");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(WIDTH, HEIGHT);
        frame.setResizable(false);
        frame.setVisible(true);
        frame.setLayout(null);
        frame.addMouseListener(this);
        frame.addKeyListener(this);
        frame.addWindowFocusListener(new WindowAdapter() {
            public void windowGainedFocus(WindowEvent e) {
                frame.requestFocusInWindow();
            }
        });
        JButton button1 = new JButton("HOW TO PLAY");
        JButton button2 = new JButton("OPTIONS");
        JButton button3 = new JButton("BEST SCORES");
        JButton button4 = new JButton("EXIT");
        button1.setBounds(0, 0, 300, 100);
        button2.setBounds(300, 0, 300, 100);
        button3.setBounds(600, 0, 300, 100);
        button4.setBounds(900, 0, 300, 100);
        refresh.add(button1);
        refresh.add(button2);
        refresh.add(button3);
        refresh.add(button4);
        refresh.setLayout(null);
        button1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				GameOver=true;
         	   	started=false;
				JOptionPane.showMessageDialog(null,"- Avoid the obstacles"+System.lineSeparator()+"- Move bird UP! Click LEFT_MOUSE or SPACE"+System.lineSeparator()+"- Are you too high? Click RIGHT_MOUSE or ARROW_DOWN"+System.lineSeparator()+"  But remember! You can use it only 3 times"+System.lineSeparator()+"- GOOD LUCK!!!");
			}
		});
        button2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				GameOver=true;
         	   	started=false;
				final JFrame mapframe = new JFrame();
				mapframe.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				frame.setTitle("Game Options");
				mapframe.setSize(400, 250);
				frame.setResizable(false);
				mapframe.setLayout(null);
				JTextField field1 = new JTextField("Maps");
				JTextField field2= new JTextField("Difficulty");
				final JCheckBox checkMars = new JCheckBox("Mars");
		        final JCheckBox checkMoon = new JCheckBox("Moon");
		        final JCheckBox checkEarth = new JCheckBox("Earth");
		        final JCheckBox checkJupiter = new JCheckBox("Jupiter");
		        final JCheckBox checkEasy = new JCheckBox("Easy");
		        final JCheckBox checkMedium = new JCheckBox("Medium");
		        final JCheckBox checkHard = new JCheckBox("Hard");
		        JButton acceptbutton = new JButton("OK");
		        field1.setBounds(20, 20, 100, 20);
		        field2.setBounds(200, 20, 100, 20);
		        field1.setEditable(false);
		        field2.setEditable(false);
		        acceptbutton.setBounds(0, mapframe.getHeight()-80,mapframe.getWidth(), 40);
		        checkMars.setBounds(20, 50, 100, 20);
		        checkMoon.setBounds(20, 80, 100, 20);
		        checkEarth.setBounds(20, 110, 100, 20);
		        checkJupiter.setBounds(20, 140, 100, 20);
		        checkEasy.setBounds(200, 50, 100, 20);
		        checkMedium.setBounds(200, 80, 100, 20);
		        checkHard.setBounds(200, 120, 100, 20);
		        mapframe.add(field1);
		        mapframe.add(field2);
 		        mapframe.add(checkMars);
		        mapframe.add(checkMoon);
		        mapframe.add(checkEarth);
		        mapframe.add(checkJupiter);
		        mapframe.add(checkEasy);
		        mapframe.add(checkMedium);
		        mapframe.add(checkHard);
		        mapframe.add(acceptbutton);
		        if(moon == true) {
		        	checkMoon.setSelected(true);
		        }
		        if(mars == true) {
		        	checkMars.setSelected(true);
		        }
		        if(earth == true) {
		        	checkEarth.setSelected(true);
		        }
		        if(jupiter == true) {
		        	checkJupiter.setSelected(true);
		        }
		        if(easy == true) {
		        	checkEasy.setSelected(true);
		        }
		        if(medium == true) {
		        	checkMedium.setSelected(true);
		        }
		        if(hard == true) {
		        	checkHard.setSelected(true);
		        }
		        checkMars.addItemListener(new ItemListener() {
					public void itemStateChanged(ItemEvent e) {
						if (e.getStateChange() == ItemEvent.SELECTED) {
							checkMoon.setSelected(false);
							checkEarth.setSelected(false);
							checkJupiter.setSelected(false);
							mars=true;
							moon=false;
							earth=false;
							jupiter=false;
						}
					}
				});
		        checkMoon.addItemListener(new ItemListener() {
					public void itemStateChanged(ItemEvent e) {
						if (e.getStateChange() == ItemEvent.SELECTED) {
							checkMars.setSelected(false);
							checkEarth.setSelected(false);
							checkJupiter.setSelected(false);
							mars = false;
							moon = true;
							earth = false;
							jupiter = false;
						}
					}
				});
		        checkEarth.addItemListener(new ItemListener() {
					public void itemStateChanged(ItemEvent e) {
						if (e.getStateChange() == ItemEvent.SELECTED) {
							checkMars.setSelected(false);
							checkMoon.setSelected(false);
							checkJupiter.setSelected(false);
							mars = false;
							moon = false;
							earth = true;
							jupiter = false;
						}
					}
				});
		        checkJupiter.addItemListener(new ItemListener() {
					public void itemStateChanged(ItemEvent e) {
						if (e.getStateChange() == ItemEvent.SELECTED) {
							checkMars.setSelected(false);
							checkMoon.setSelected(false);
							checkEarth.setSelected(false);
							jupiter = true;
							mars = false;
							moon = false;
							earth = false;
						}
					}
				});
		        checkEasy.addItemListener(new ItemListener() {
					public void itemStateChanged(ItemEvent e) {
						if (e.getStateChange() == ItemEvent.SELECTED) {
							checkMedium.setSelected(false);
							checkHard.setSelected(false);
							easy = true;
							medium = false;
							hard = false;
						}
					}
				});
		        checkMedium.addItemListener(new ItemListener() {
					public void itemStateChanged(ItemEvent e) {
						if (e.getStateChange() == ItemEvent.SELECTED) {
							checkEasy.setSelected(false);
							checkHard.setSelected(false);
							easy = false;
							medium = true;
							hard = false;
						}
					}
				});
		        checkHard.addItemListener(new ItemListener() {
					public void itemStateChanged(ItemEvent e) {
						if (e.getStateChange() == ItemEvent.SELECTED) {
							checkMedium.setSelected(false);
							checkEasy.setSelected(false);
							easy = false;
							medium = false;
							hard = true;
						}
					}
				});
		        acceptbutton.addActionListener(new ActionListener() {
		            public void actionPerformed(ActionEvent e) {
		            	mapframe.dispose();
		            }
		        });
				mapframe.setVisible(true);
				frame.addFocusListener(new FocusListener() {
					public void focusLost(FocusEvent e) {
					}
					public void focusGained(FocusEvent e) {
						mapframe.dispose();
					}
				});
			}
		});
        button3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null, "1."+firstName +"   "+ firstScore+System.lineSeparator()+"2."+secondName +"   "+ secondScore+System.lineSeparator()+"3."+thirdName +"   "+ thirdScore);
			}
		});
        button4.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        bird = new Rectangle(200, HEIGHT/4+50, 70,70);
        columns = new ArrayList<Rectangle>();
        thirdName = "---";
        secondName = "---";
        firstName = "---";
        moon = true;
        medium = true;
        if (started == !true){
        createColumnsTimerEasy.stop();
        createColumnsTimerMedium.stop();
        createColumnsTimerHard.stop();
        gametimer.stop();
        }
        else{
        	createColumnsTimerEasy.start();
        	createColumnsTimerMedium.start();
        	createColumnsTimerHard.start();
        	gametimer.start();
        }
        try {
        	if (moon == true){
        		backgroundImage = ImageIO.read(Flappy.class.getResource("/backgroundMoon.png"));
                columnImage = ImageIO.read(Flappy.class.getResource("/asteroid.png"));
        	}
            birdImage = ImageIO.read(Flappy.class.getResource("/birdcolor70.png"));
            birdBufferredImage = ImageIO.read(Flappy.class.getResource("/birdcolor70.png"));
            livesImage = ImageIO.read(Flappy.class.getResource("/heart.png"));
        } catch (IOException e1) {
        	System.out.println("Images loading ERROR");
            e1.printStackTrace();
        }
    }
    
    public void addColumn(boolean start) {
        int space = 300;
        int width = 150;
        int height = 50 + rand.nextInt(300);
        if (start == true) {
            columns.add(new Rectangle(WIDTH + width, HEIGHT - height , width, height));
            columns.add(new Rectangle(WIDTH + width, 100, width, HEIGHT - height - space));
            }
    }
 
   
    public void paintColumn(Graphics g, Rectangle column) {
        g.setColor(Color.green);
        g.fillRect(column.x, column.y, column.width, column.height);
        g.drawImage(columnImage, column.x, column.y, column.width, column.height, null);
    }
   
    public void rescue(){
    	if (GameOver!=true && lives>0){
    		bird.y+=100;
    		lives--;
    	}
    }
    
    public void jump(){
    	if (GameOver==true){
    		try {
	        	if (mars==true){
	        		backgroundImage = ImageIO.read(Flappy.class.getResource("/backgroundMars.jpg"));
	        		columnImage = ImageIO.read(Flappy.class.getResource("/bricks.jpg"));
	        	}
	        	else if (moon==true){
	        		backgroundImage = ImageIO.read(Flappy.class.getResource("/backgroundMoon.png"));
	        		columnImage = ImageIO.read(Flappy.class.getResource("/asteroid.png"));
	        	}
	        	else if (earth==true){
	        		backgroundImage = ImageIO.read(Flappy.class.getResource("/backgroundEarth.png"));
	        		columnImage = ImageIO.read(Flappy.class.getResource("/log.jpg"));
	        	}
	        	else if (jupiter==true){
	        		backgroundImage = ImageIO.read(Flappy.class.getResource("/backgroundJupiter.png"));
	        		columnImage = ImageIO.read(Flappy.class.getResource("/solarpanel.png"));
	        	}
	        	birdImage = ImageIO.read(Flappy.class.getResource("/birdcolor70.png"));
	        	livesImage = ImageIO.read(Flappy.class.getResource("/heart.png"));
	        } catch (IOException e1) {
	        	System.out.println("Images loading ERROR");
	            e1.printStackTrace();
	        }
    		if(score/2>10){
	    		if(score/2>=firstScore){
	    			String inputValue1 = JOptionPane.showInputDialog("Wpisz swoje imiê");
	    			thirdName=secondName;
	    			thirdScore=secondScore;
	    			secondName=firstName;
	    			secondScore=firstScore;
	    			firstName=inputValue1;
	    			firstScore=score/2;
	    		}
	    		else if(score/2>=secondScore){
	    			String inputValue2 = JOptionPane.showInputDialog("Wpisz swoje imiê");
	    			thirdName=secondName;
	    			thirdScore=secondScore;
	    			secondName=inputValue2;
	    			secondScore=score/2;
	    		}
	    		else if(score/2>=thirdScore){
	    			String inputValue3 = JOptionPane.showInputDialog("Wpisz swoje imiê");
	    			thirdName=inputValue3;
	    			thirdScore=score/2;
	    		}
    		}
    		columns.clear();
    		score=0;
    		bird.x=200;
			bird.y=HEIGHT/4+50;
			lives=3;
			started=true;
    		GameOver=false;
    	}
	    else {
	    	if (hard==true){
	    		bird.y-=90;
	    	}
	    	else{
	    		bird.y-=50;
	    	}
	    }
    }
    
    public void actionPerformed(ActionEvent e) {
        int speed = 7;
        if (easy == true){
        	speed = seasy;
        }
        if (medium == true){
        	speed = smedium;
        }
        if (hard == true){
        	speed = shard;
        }
        double gravity = 10;
        if (mars == true){
        	gravity = gmars;
        }
        if (moon == true){
        	gravity = gmoon;
        }
        if (earth == true){
        	gravity = gearth;
        }
        if (jupiter == true){
        	gravity = gjupiter;
        }
        if (started) {
           bird.y += gravity;
           if (bird.y >= HEIGHT-bird.height-29) {
        	   bird.y = HEIGHT-bird.height-29;
           }
           if (bird.y <= 100){
        	   bird.y = 100;
           }
           for (int i = 0; i < columns.size(); i++) {
                Rectangle column = columns.get(i);
                column.x -= speed;
           }
           for (int i = 0; i < columns.size(); i++) {
                Rectangle column = columns.get(i);
                if (column.x + column.width < 0) {
                    columns.remove(column);
                    i--;
                }
                for (int j = speed; j > 0; j--)
                if (column.x == (50 - j)) {
                    score++;
                }
            }
           if (bird.y >= HEIGHT - bird.height - 29){
               GameOver = true;
               started = false;
           }
           for (int i = 0; i < columns.size(); i++) {
               Rectangle column = columns.get(i);
               if (bird.intersects(column)){
                   double x0 = column.getX();
                   double y0 = column.getY();
                   double y1 = y0 + column.getHeight();
	               for (int j = 0; j < birdBufferredImage.getWidth(); j++){
	            	   for (int k = 0; k < birdBufferredImage.getHeight(); k++){
	            		   	if (bird.getX() + j > x0 && bird.getY() + k < y1 && bird.getY() + k > y0){
	            		   			Color c = new Color(birdBufferredImage.getRGB(j, k),true);
	            		   			if (c.getAlpha() != 0) {
	            		   				GameOver = true;
	            		   				started = false;
	            		   				k = birdBufferredImage.getHeight();
	            		   				j = birdBufferredImage.getWidth();
	                               }
	                           }
	                       }
	                   }
               }
           }
        }
        refresh.repaint();
    }
 
    public void repaint(Graphics g) {
        g.drawImage(backgroundImage, 0, 0, null);
        for (Rectangle column : columns) {
            paintColumn(g, column);
        	}
        g.setColor(Color.white);
		g.drawImage(birdImage, bird.x, bird.y, bird.width, bird.height, null);
		g.setColor(Color.black);
		g.fillRect(0, 725, 65, 65);
		g.setColor(Color.yellow);
		g.drawRect(0, 725, 65, 65);
		g.setFont(new Font("Arial", 1, 15));
		g.drawString("SCORE", 5, 740);
		g.setFont(new Font("Arial", 1, 30));
		g.drawString(String.valueOf(score/2), 5, 765);
		g.drawRect(10, 120, 30, 30);
		g.drawRect(40, 120, 30, 30);
		g.drawRect(70, 120, 30, 30);
		if(lives > 0){
			g.drawImage(livesImage,10, 120, 30, 30, null);
		}
		if(lives > 1){
			g.drawImage(livesImage,40, 120, 30, 30, null);
		}
		if(lives > 2){
			g.drawImage(livesImage,70, 120, 30, 30, null);
		}
		if (GameOver)
		{
			g.setColor(Color.gray);
			g.fillRect(WIDTH/3+100-10, HEIGHT / 2-50, 200+20, 200-30);
			g.setColor(Color.white);
			g.drawString("  Game Over!", WIDTH/3+100, HEIGHT / 2);
			g.drawString("Your score is:", WIDTH/3+100, HEIGHT / 2 +50);
			g.drawString("           "+String.valueOf(score/2), WIDTH/3+100, HEIGHT / 2 +100);
		}
    }
 
    public class Refresh extends JLayeredPane{
    	private static final long serialVersionUID = 1L;
    	protected void paintComponent(Graphics g){
    		super.paintComponent(g);
    		Flappy.flappyBird.repaint(g);
    	}
    }
   
    public static void main(String[] args) {
    	started = true;
        flappyBird = new Flappy();
    }
    public void mouseClicked(MouseEvent e) {
    	if (SwingUtilities.isLeftMouseButton(e) == true) {
    		jump();
		}
    	if (SwingUtilities.isRightMouseButton(e) == true) {
			rescue();
		}
	}
	public void mouseEntered(MouseEvent arg0) {
	}
	public void mouseExited(MouseEvent arg0) {
	}
	public void mousePressed(MouseEvent arg0) {
	}
	public void mouseReleased(MouseEvent arg0) {
	}
	public void keyPressed(KeyEvent e) {
	}
	public void keyReleased(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_SPACE) {
			jump();
		}
		if (e.getKeyCode() == KeyEvent.VK_DOWN) {
			rescue();
		}
	}
	public void keyTyped(KeyEvent e) {
	}
}