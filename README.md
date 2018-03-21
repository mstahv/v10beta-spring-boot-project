# Vaadin 10 workshop for Java developers with previous Vaadin experience.

Contains a simple database backed domain model and a very rough framework for application with two Vaadin view. Based on this we will accomplish an application capable of listing and editing entities, visualising the data using GoogleMap integration (that we build from bits) and Vaadin Charts. You'll also learn to use Designer. The length of the workshop can be adjusted quite a lot by leaving parts out or incomplete.

You'll learn that:

 * Developing in plain Java with Vaadin 10 can be as simple as it used to be with previous versions
 * It is easier to "get to the metal", when needed. 
 * How to integrate a basic Web Component to your application
 * How to create field implementations for custom data types
 * How to use Vaadin Designer to modify HTML templates
 * How to use the Polymer inspired data binding mechanism with HTML templates
 * How to add and use Vaadin Charts add-on in your project

The instructions here are not supposed to be complete, so you need basic Java skills, previous Vaadin Framework (6,7,8) experience or passing of the basic Vaadin Flow Tutorial, and probably a bit help from https://vaadin.com/docs.

Work alone or in pairs. Workshop instructor will do the same exercise with you and guide your if needed. Feel free to ping the instructor whenever needed.

### Part 1: Create a basic listing of Person objects with a separate form for editing

This part is almost like plain old Vaadin Framework 8 development. You'll get a small rehearsal and confidence that you can still do this with Vaadin 10 ;-)

 1. Checkout the project, either with git clone or just by downloading the zip.
 1. Import the project to your IDE and give a rough overview to the files that exists in the project
 1. Create a Grid to PersonView and populate it form the injected repository
 1. Create a basic form that contains fields "name", "email", "birthday". Either inline using Java or with Vaadin Designer.
 1. Add a single select value listener to Grid and bind member fields to selected bean
 1. Add a Save button, which saves the new state to the repository and updates the listing with fresh values from the DB (gotcha: bug in beta Flow, you have to call grid.getDataCommunicator().refresh(bean); to make new value displayed )

### Part 2: Create a google-map integration to visualize the location field in the Person

In previous Vaadin versions, client side extensions were most often built with GWT (in Java). This is the largest change in Vaadin 10 (in addition to the component API renewal) and for many also the most scary one. In this part we will create a really simple integration for Google Maps. Basically we'll wrap the provided Web Component and build an easy to use Java API for it.

 1. Use vaadin.com/start with input parameter: https://github.com/GoogleWebComponents/google-map
 1. Import the generated project to IDE
 1. Define default size (e.g. 300*300px) for the GoogleMap component, otherwise it will be too small
 1. Test the component by running `jetty:run` goal
 1. Define a new component, GoogleMapMarker, by copy pasting the GoogleMap component. Change it to be for tag: google-map-marker
 1. Add Java APIs for latitude and longitude, that pass the values to corresponding properties in the web component
 1. Also add lon/lat Java APIs for the GoogleMap. Don't try to use fitToMarkers property instead as it currently has some integration issues with Flow.
 1. Optionally add some other Java API for map or Marker, see the google-map Web Component page for inspiration what could be implemented
 
### Part 3: Use the component in the application

In this part we'll add the GoogleMap integration as a dependency to the application project and use it to show the location of the Person.

 1. Run mvn:install in the component project
 1. Add dependency to your application projects pom.xml
 1. Modify the PersonView to show the location on the GoogleMap when a person is selected from the Grid. At this point you'll need to do that "manually". Yes, this is bit ugly, but we'll fix this in next step

### Part 4: Make an editable location field using the Google Map integration

The data type for location field is JTS Point (com.vividsolutions.jts.geom.Point). For a cleaner integration, we'll build a Binder compatible field component to edit this datatype and replace the ugly manual integration we hacked together in the last part. 

 1. Add JTS dependency to your GoogleMap project. Maven coordinates: com.vividsolutions:jts:1.13
 1. Create new class, PointField extending from GoogleMap
 1. Make it implement HasValue<PointField, com.vividsolutions.jts.geom.Point>
 1. In setValue method, pass the location to a marker that you show on map. Finetune the solution so that also the map gets centered to the marker.
 1. At this point make leave it "read-only" and test it in the application project. Now you can use it like any other Field and bind the value using Binder
 1. Optional: Create a similar test to the add-on project
 1. STOP HERE for now! The rest of this part is ON HOLD, until certain things gets fixed in Flow/google-map element. In theory this should be easy, in practice: don't try this at home yet :-)
 1. Make the GoogleMapMarker in the field draggable and synchronise longitude and latitude properties automatically to the server on drag events
 1. Expose drag end event to the server side
 1. Implement getValue method properly in PointField. See hints below if you are new to JTS API.
 1. Try the complete PointField in the application project
 
### Part 5: Try Vaadin Designer

Now that Vaadin 10 has more advanced HTML templating support, Vaadin Designer also becomes handier than ever. In this part you'll learn about the new HTML templates and the two-way databinding support.

 1. Install Vaadin Designer and enable beta features (if still in beta)
 1. Open the splash view template in Vaadin Designer and try modifying the content and adding new components.
 1. Create a text showing the number of persons in the database and bind the value using the Polymer syntax. 
 1. Optional: Try creating the same form we did earlier in plain Java using Vaadin Designer

### Part 6: Visualize age distribution using Vaadin Charts

Vaadin Charts is still the handiest way to build impressive data visualisations to your application. Let's try it also in Vaadin 10.

 1. Add Vaadin Charts to the project
 1. Add a dummy column chart to the splash screen
 1. Get the data from PersonRepository and group ages of persons (0-9, 10-19, etc) and visualize the results using the column chart.

## Code hints

Some hints of tricky but irrelevant parts of the exercise.

#### Creating a JTS Point:

     GeometryFactory factory = new GeometryFactory(new PrecisionModel(), 4326);
     
     @Override
     public Point getValue() {
         return factory.createPoint(new Coordinate(marker.getLongitude(), marker.getLatitude()));
     }

