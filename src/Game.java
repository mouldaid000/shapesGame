/**
 * Created by mouldaid000 on 2/7/2017.
 */
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class Game extends JPanel implements ActionListener {

    Timer timer;
    int positionX, positionY;
    ArrayList<Entity> entities;

    public Game(){
        //Sets up JFrame properties
        JFrame frame = new JFrame();
        frame.setVisible(true);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setTitle("SHAPES");
        setPreferredSize(new Dimension(600,800));
        setBackground(Color.black);
        frame.add(this);

        addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseMoved(MouseEvent e){
                super.mouseMoved(e);
                positionX = e.getX();
                positionY = e.getY();
            }
        });


        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                super.mouseEntered(e);
                setCursor(getToolkit().createCustomCursor(new BufferedImage(3,3, BufferedImage.TYPE_INT_ARGB), new Point(0,0),null));
            }
        });



        frame.pack();
        frame.setLocationRelativeTo(null);
    }



    public static void main(String[] args){
        Game game = new Game();
        game.init();
        game.run();
    }


    @Override
    public void actionPerformed(ActionEvent e) {

        collisions();
        entities.get(0).playerMove();
        for(int i = 1; i < entities.size(); i++){
            entities.get(i).move();
        }
        repaint();
    }

    public void init(){
        entities = new ArrayList<Entity>();
        entities.add(new Circle(Color.red, getWidth()/2, getHeight()/2, 50, this));
        for(int i = 0; i < 1000; i++){
            entities.add(new Food(Color.green, (int)(25 + (getWidth()-100)*Math.random()), (int)(25 + (getHeight()-50)*Math.random()),30, 30, this));
        }
        for(int i = 0; i < 3; i++){
            entities.add(new Circle(Color.blue, (int)(25 + (getWidth()-100)*Math.random()), (int)(25 + (getHeight()-50)*Math.random()),30, this));
        }
    }

    public void collisions(){
        for(int i = 1; i < entities.size(); i++){
            if(entities.get(0).collides(entities.get(i))){
                if(entities.get(i) instanceof Food){
                    entities.remove(i);
                }else if(entities.get(i) instanceof Circle){
                    JOptionPane.showMessageDialog(null, "You died.");
                    System.exit(0);
                }
            }
        }
    }

    public void run(){
        timer = new Timer(1000/60, this);
        timer.start();
    }

    public void paint(Graphics g){
        super.paint(g);
        for(Entity obj : entities){
            obj.paint(g);
        }
    }
}