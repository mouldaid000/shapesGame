import java.awt.*;

/**
 * Created by mouldaid000 on 2/7/2017.
 */
public class Circle extends Entity{

    public Circle(Color color, int x, int y, int diameter, Game game){

        super(color, x, y, diameter, diameter, game);
    }

    public void paint(Graphics g){
        g.setColor(getColor());
        g.fillOval(getX(), getY(), getWidth(), getHeight());
    }
}
