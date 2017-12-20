package Graphs;

import javafx.application.Application;
import javafx.geometry.Point2D;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

public class ConnectedCircles extends Application
{

    @Override
    public void start(Stage primaryStage) throws Exception
    {
        //Create a scene and place it in the stage
        Scene scene = new Scene(new CirclePane(), 450, 350);
        primaryStage.setTitle("ConnectedCircles"); //set the stage title
        primaryStage.setScene(scene); //place the scene in the stage
        primaryStage.show(); //display the stage
    }

    //Pane for displaying circles
    class CirclePane extends Pane
    {
        public CirclePane()
        {
            this.setOnMouseClicked(e ->
            {
                if (!isInsideACircle(new Point2D(e.getX(), e.getY())))
                {
                    //add a new circle
                    getChildren().add(new Circle(e.getX(), e.getY(), 20));
                    colorIfConnected();
                }
            });
        }

        private void colorIfConnected()
        {
            if (getChildren().size() == 0)
            {
                return; //no circles in the pane
            }

            //build the edges
            List<Edge> edges = new ArrayList<>();
            for (int i = 0; i < getChildren().size(); i++)
            {
                for (int j = 0; j < getChildren().size(); j++)
                {
                    if (overlaps((Circle) (getChildren().get(i)), (Circle) getChildren().get(j)))
                    {
                        edges.add(new Edge(i, j));
                        edges.add(new Edge(j, i));
                    }
                }
            }

            //Create a graph with circles as vertices
            IGraph<Node> graph = new UnweightedGraph<>(getChildren(), edges);
            AbstractGraph<Node>.Tree tree = graph.dfs(0);
            boolean isAllCirclesConnected = getChildren().size() == tree.getNumberOfVerticesFound();


            for (Node circle: getChildren())
            {
                ((Circle) circle).setStroke(Color.BLACK);
                if (isAllCirclesConnected)
                {
                    ((Circle) circle).setFill(Color.RED);
                } else
                {
                    ((Circle) circle).setFill(Color.WHITE);

                }
            }
        }

        private boolean overlaps(Circle circle1, Circle circle2)
        {
            return new Point2D(circle1.getCenterX(), circle1.getCenterY()).distance(circle2.getCenterX(),
                    circle2.getCenterY()) <= circle1.getRadius() + circle2.getRadius();
        }

        private boolean isInsideACircle(Point2D p)
        {
            for (Node circle : this.getChildren())
            {
                if (circle.contains(p))
                {
                    return true;
                }
            }
            return false;
        }
    }

}
