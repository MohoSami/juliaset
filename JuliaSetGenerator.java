/**
 * @author      Mohammed Sami <mohammed.sami23@trinityschoolnyc.org>
 * @version     1.0
 * @since       2022-02-28
 */
import java.awt.Color;

public class JuliaSetGenerator{

/**
 * recursive equation for mandelbrot
 * @method zEquation
 * @param  C                       ComplexNumber C to be added in mandelbrot equation
 * @param  prev                    ComplexNumber prev from previous recursion to be squared
 * @param  count                   double count of how many times the recursion happens
 * @return           [description]
 */
  public static double zEquation(ComplexNumber C, ComplexNumber prev, double count){
    ComplexNumber z = new ComplexNumber();
    count += 1;
    z = prev.square().add(C);
    if(z.magnitude() >= 2.0){
      return count;
    } else if(count > 255.0){
      return count;
    } else {
      return zEquation(C, z, count);
    }
  }

/**
 * generates juliaset
 * @method juliasetGen
 * @param  w              DisplayWindow to display in
 * @param  clickedCoords  ComplexNumber point clicked on Mandelbrot
 */
  public static void juliasetGen(DisplayWindow w, ComplexNumber clickedCoords){
    ComplexNumber z0 = clickedCoords;
    for(double a = w.getRMin(); a < w.getRMax(); a+=w.getStepSize()){
      for(double b = w.getIMin(); b < w.getIMax(); b+= w.getStepSize()){
        ComplexNumber C = new ComplexNumber(a, b);
        double count = 0;
        count = zEquation(z0, C, count);
        float hue = (float) count;
        hue = hue * 750/255;
        if(count >= 255.0){
          StdDraw.setPenColor(StdDraw.BLACK);
        } else {
          StdDraw.setPenColor(Color.getHSBColor(hue, 1.0f, 1.0f));
        }
        StdDraw.filledCircle(w.mapX(C.getReal()), w.mapY(C.getImaginary()), 1);
      }
    }
  }

/**
 * generates mandelbrot
 * @method MandelbrotsetGen
 * @param  w                 DisplayWindow to display mandelbrot in
 */
  public static void MandelbrotsetGen(DisplayWindow w){
    ComplexNumber z0 = new ComplexNumber();
    for(double a = w.getRMin(); a < w.getRMax(); a+=w.getStepSize()){
      for(double b = w.getIMin(); b < w.getIMax(); b+= w.getStepSize()){
        ComplexNumber C = new ComplexNumber(a, b);
        double count = 0;
        count = zEquation(C, z0, count);
        float hue = (float) count;
        hue = hue * 750/255; //shoutout to Diya for helping me find the right color multiplier
        if(count >= 255.0){
          StdDraw.setPenColor(StdDraw.BLACK);
        } else {
          StdDraw.setPenColor(Color.getHSBColor(hue, 1.0f, 1.0f));
        }
        StdDraw.filledCircle(w.mapX(C.getReal()), w.mapY(C.getImaginary()), 1);
      }
    }
  }

  public static void main(String args[]){
    StdDraw.setCanvasSize(800,300);
    StdDraw.setXscale(0,800);
    StdDraw.setYscale(0,300);
    StdDraw.enableDoubleBuffering();
    DisplayWindow leftWindow = new DisplayWindow(0,0,400,300);
    DisplayWindow rightWindow = new DisplayWindow(400,0,400,300);

    ComplexNumber clickedCoords = new ComplexNumber();
while(true){
  MandelbrotsetGen(leftWindow);
  juliasetGen(rightWindow, clickedCoords);

  if(StdDraw.mousePressed() == true){
  if(StdDraw.mouseX()<=400){
    double pressedReal = leftWindow.mapA(StdDraw.mouseX());
    double pressedImaginary = leftWindow.mapB(StdDraw.mouseY());
    ComplexNumber toDisplay = new ComplexNumber(pressedReal, pressedImaginary);
    StdDraw.setPenColor(StdDraw.BLACK);
    StdDraw.text(50, 200, toDisplay.toString());

    clickedCoords = toDisplay;
    leftWindow.recenter(toDisplay);
    System.out.println(toDisplay.toString());
  } else {
    double pressedReal = rightWindow.mapA(StdDraw.mouseX());
    double pressedImaginary = rightWindow.mapB(StdDraw.mouseY());
    ComplexNumber toDisplay = new ComplexNumber(pressedReal, pressedImaginary);
    StdDraw.setPenColor(StdDraw.BLACK);
    StdDraw.text(150, 50, toDisplay.toString());

    rightWindow.recenter(toDisplay);
  }
}

  if (StdDraw.isKeyPressed(16) == false && StdDraw.isKeyPressed(73) == true){
    leftWindow.zoomIn();
  }
  if (StdDraw.isKeyPressed(16) == false && StdDraw.isKeyPressed(79) == true){
    leftWindow.zoomOut();
  }
  if (StdDraw.isKeyPressed(16) == true && StdDraw.isKeyPressed(73) == true){
    rightWindow.zoomIn();
  }
  if (StdDraw.isKeyPressed(16) == true && StdDraw.isKeyPressed(79) == true){
    rightWindow.zoomOut();
  }
  if (StdDraw.isKeyPressed(16) == false && StdDraw.isKeyPressed(82) == true){
    leftWindow.resetView();
  }
  if (StdDraw.isKeyPressed(16) == true && StdDraw.isKeyPressed(82) == true){
    rightWindow.resetView();
  }

  StdDraw.show();
}






  }

}
