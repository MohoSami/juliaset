/**
 * @author      Mohammed Sami <mohammed.sami23@trinityschoolnyc.org>
 * @version     1.0
 * @since       2022-03-30
 */
public class DisplayWindow{

  private double leftX;
  private double bottomY;
  private double length;
  private double width;
  private double rMax, rMin;
  private double iMax, iMin;
  private double zoomFactor;
  private ComplexNumber center;

/**
 * constructor for DisplayWindow data type
 * @method DisplayWindow constructor
 * @param  leftX          beginning x coord in xy plane
 * @param  bottomY        beginning y coord in xy plane
 * @param  length         length of DisplayWindow in terms of x in xy plane
 * @param  width          width of DisplayWindow in terms of y in xy plane
 */
  public DisplayWindow(double leftX, double bottomY, double length, double width){
    this.leftX = leftX;
    this.bottomY = bottomY;
    this.length = length;
    this.width = width;
    this.rMax = 2;
    this.rMin = -2;
    this.iMin = -1.5;
    this.iMax = 1.5;
    this.center = new ComplexNumber(0, 0);
    this.zoomFactor = 1;
  }

/**
 * returns the private leftX value for the DisplayWindow
 * @method getLeftX
 * @return double leftX
 */
  public double getLeftX(){
    return this.leftX;
  }

/**
 * returns private iMax value for the DisplayWindow
 * @method getIMax
 * @return double iMax
 */
  public double getIMax(){
    return this.iMax;
  }

/**
 * returns private iMin value for the DisplayWindow
 * @method getIMin
 * @return double iMin
 */
  public double getIMin(){
    return this.iMin;
  }

/**
 * returns private rMax value for DisplayWindow
 * @method getRMax
 * @return double rMax
 */
  public double getRMax(){
    return this.rMax;
  }

/**
 * returns private rMin value for DisplayWindow
 * @method getRMin
 * @return double rMin
 */
  public double getRMin(){
    return this.rMin;
  }

/**
 * maps real portion of ComplexNumber to an x value on xy plane
 * @method mapX
 * @param  a                  double value to map to x
 * @return      double x value
 */
  public double mapX(double a){
    return map(a, this.rMin, this.rMax, this.leftX, this.leftX + this.length) + this.leftX;
  }

/**
 * maps real imaginary portion of ComplexNumber to a y value on xy plane
 * @method mapY
 * @param  b                  double value to map to y
 * @return      double y value
 */
  public double mapY(double b){
    return map(b, this.iMin, this.iMax, this.bottomY, this.bottomY + this.width) + this.bottomY;
  }

/**
 * maps x point on xy plane to corresponding ComplexNumber
 * @method mapA
 * @param  windowX               x value of point to map
 * @return         double real value
 */
  public double mapA(double windowX){
    return map(windowX, this.leftX, this.leftX + this.length, this.rMin, this.rMax) + this.rMin;
  }

/**
 * maps y point on xy plane to corresponding ComplexNumber
 * @method mapB
 * @param  windowY               y value of point to map
 * @return         double imaginary value
 */
  public double mapB(double windowY){
    return map(windowY, this.bottomY, this.bottomY + this.width, this.iMin, this.iMax) + this.iMin;
  }

/**
 * maps a value of one range to another using the parameters given
 * @method map
 * @param  value                value to map
 * @param  oldMin               initial minimum
 * @param  oldMax               initial maximum
 * @param  newMin               target minimum
 * @param  newMax               target maximum
 * @return        double value mapped
 */
  public static double map(double value, double oldMin, double oldMax, double newMin, double newMax){
    double result = ((value-oldMin)/(oldMax-oldMin))*(newMax-newMin);
    return result;
  }

/**
 * testing the DisplayWindow class
 * @method main
 * @param  args  nothing honestly
 */
  public static void main(String[] args){
    DisplayWindow dw = new DisplayWindow(400, 0, 400, 300);
    System.out.println(dw.mapX(-1));
    System.out.println(dw.mapY(-1));
    System.out.println(dw.mapA(800));
  }

/**
 * resets all values to default
 * @method resetView
 */
  public void resetView(){
    this.rMax = 2;
    this.rMin = -2;
    this.iMin = -1.5;
    this.iMax = 1.5;
    this.center = new ComplexNumber(0, 0);
    this.zoomFactor = 1;
  }

/**
 * adjusts zoom factor to zoom out of DisplayWindow
 * @method zoomOut
 */
  public void zoomOut(){
    this.zoomFactor *= 1.25;
    this.updateZoom();
  }

/**
 * adjusts zoom factor to zoom into DisplayWindow
 * @method zoomIn
 */
  public void zoomIn(){
    this.zoomFactor *= 0.8;
    this.updateZoom();
  }

  /**
   * uses zoom factor to adjust the rMin, rMax, iMin, iMax values of a DisplayWindow. This terrific updateZoom method idea that adheres to the principles of DRY was inspired by Ben Levy
   * @method updateZoom
   */
  public void updateZoom(){
    this.rMin = this.center.getReal() - (2 * this.zoomFactor);
    this.rMax = this.center.getReal() + (2 * this.zoomFactor);
    this.iMin = this.center.getImaginary() - (1.5 * this.zoomFactor);
    this.iMax = this.center.getImaginary() + (1.5 * this.zoomFactor);
   }

   /** 
    * returns double to be used as an increment in for loop in JuliaSetGenerator
    * @method getStepSize
    * @return double step size
    */
  public double getStepSize(){
    return (this.rMax-this.rMin)/this.length;
  }

/**
 * centers the DisplayWindow on a given ComplexNumber
 * @method recenter
 * @param  c         ComplexNumber to center around
 */
  public void recenter(ComplexNumber c){
    this.center = c;
    this.updateZoom();
  }


}
