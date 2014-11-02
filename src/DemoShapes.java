import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

public class DemoShapes {
    public static final Color DEFAULT_COLOR = Color.BLACK;
    public static final Color DEFAULT_BORDER_COLOR = Color.GREEN;
    public static int fields = 8;

    public DemoShapes() {
        List<ShapeItem> shapes = new ArrayList<ShapeItem>();

        for (int i=0; i<fields; i++) {
            for (int j=0; j<fields; j++) {

                shapes.add(new ShapeItem(new Rectangle2D.Double(i*80, j*80, 80, 80),
                        DEFAULT_BORDER_COLOR));
                shapes.add(new ShapeItem(new Rectangle2D.Double(i*80, j*80, 79, 79),
                        DEFAULT_COLOR));

            }

        }


        JFrame frame = new JFrame("Shapes");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        ShapesPanel panel = new ShapesPanel(shapes);
        frame.add(panel);

        frame.setLocationByPlatform(true);
        frame.pack();
        frame.setVisible(true);
    }

    class ShapeItem {
        private Shape shape;
        private Color color;

        public ShapeItem(Shape shape, Color color) {
            super();
            this.shape = shape;
            this.color = color;
        }

        public Shape getShape() {
            return shape;
        }

        public void setShape(Shape shape) {
            this.shape = shape;
        }

       public Color getColor() {
            return color;
        }

        public void setColor(Color color) {
            this.color = color;
        }
    }

    class ShapesPanel extends JPanel {
        private List<ShapeItem> shapes;
        private Random rand = new Random();

        public ShapesPanel(List<ShapeItem> shapesList) {
            this.shapes = shapesList;

            addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {

                    Color color = getRandomColor();
                    for (ShapeItem item : shapes) {
                        if (item.getShape().contains(e.getPoint())) {
                           item.setColor(color);
                            System.out.println (color);
                        }
                    }
                    repaint();
                }
            });
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);

            Graphics2D g2 = (Graphics2D) g.create();

            for (ShapeItem item : shapes) {
                g2.setColor(item.getColor());
                g2.fill(item.getShape());
            }

            g2.dispose();
        }

        @Override
        public Dimension getPreferredSize() {
            return new Dimension(640, 640);
        }

        private Color getRandomColor() {
            return new Color(rand.nextFloat(), rand.nextFloat(),
                    rand.nextFloat());
        }
    }

}