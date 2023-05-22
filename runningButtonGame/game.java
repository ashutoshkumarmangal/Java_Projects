import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class game extends JFrame {

    private int currentPos = 0;
    private JPanel container;

    public game() {
        // set up the frame
        setTitle("Running Button Game");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(400, 200));
        setLayout(new BorderLayout());

        // create the buttons
        JButton helloButton = new JButton("Hello");
        JButton thisButton = new JButton("This");
        JButton isButton = new JButton("Is");
        JButton jscriptButton = new JButton("Jscript");

        // create the container and add the buttons
        container = new JPanel();
        container.setLayout(new BoxLayout(container, BoxLayout.Y_AXIS));
        container.add(Box.createVerticalGlue());
        container.add(helloButton);
        container.add(Box.createRigidArea(new Dimension(0, 10)));
        container.add(thisButton);
        container.add(Box.createRigidArea(new Dimension(0, 10)));
        container.add(isButton);
        container.add(Box.createRigidArea(new Dimension(0, 10)));
        container.add(jscriptButton);
        container.add(Box.createVerticalGlue());

        // create the previous and next buttons
        JButton prevButton = new JButton("<<");
        JButton nextButton = new JButton(">>");

        // add action listeners to the previous and next buttons
        prevButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                currentPos = (currentPos - 1 + container.getComponentCount() - 1) % (container.getComponentCount() - 1);
                container.remove(jscriptButton);
                container.add(jscriptButton, currentPos);
                container.revalidate();
                container.repaint();
            }
        });

        nextButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                currentPos = (currentPos + 1) % (container.getComponentCount() - 1);
                container.remove(jscriptButton);
                container.add(jscriptButton, currentPos);
                container.revalidate();
                container.repaint();
            }
        });

        // add the container and the previous and next buttons to the frame
        add(container, BorderLayout.CENTER);
        add(prevButton, BorderLayout.WEST);
        add(nextButton, BorderLayout.EAST);

        // show the frame
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public static void main(String[] args) {
        new game();
    }
}
