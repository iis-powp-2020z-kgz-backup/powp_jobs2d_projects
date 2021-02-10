package edu.kis.powp.jobs2d;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.util.logging.Level;
import java.util.logging.Logger;

import edu.kis.legacy.drawer.panel.DrawPanelController;
import edu.kis.legacy.drawer.shape.LineFactory;
import edu.kis.powp.appbase.Application;
import edu.kis.powp.jobs2d.command.RectangleCanvas;
import edu.kis.powp.jobs2d.command.gui.CommandManagerWindow;
import edu.kis.powp.jobs2d.command.gui.CommandManagerWindowCommandChangeObserver;
import edu.kis.powp.jobs2d.drivers.adapter.LineDriverAdapter;
import edu.kis.powp.jobs2d.drivers.transformation.Rotate;
import edu.kis.powp.jobs2d.drivers.transformation.Scale;
import edu.kis.powp.jobs2d.drivers.transformation.Transformation;
import edu.kis.powp.jobs2d.drivers.transformation.TransformationDriver;
import edu.kis.powp.jobs2d.events.*;
import edu.kis.powp.jobs2d.events.SelectRunCurrentCommandOptionListener;
import edu.kis.powp.jobs2d.events.SelectTestFigure2OptionListener;
import edu.kis.powp.jobs2d.events.SelectTestFigureOptionListener;


import edu.kis.powp.jobs2d.features.*;


public class TestJobs2dApp {
	private final static Logger logger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

	/**
	 * Setup test concerning preset figures in context.
	 *
	 * @param application Application context.
	 */
	private static void setupPresetTests(Application application) {
		SelectTestFigureOptionListener selectTestFigureOptionListener = new SelectTestFigureOptionListener(
				DriverFeature.getDriverManager());
		SelectTestFigure2OptionListener selectTestFigure2OptionListener = new SelectTestFigure2OptionListener(
				DriverFeature.getDriverManager());

		application.addTest("Figure Joe 1", selectTestFigureOptionListener);
		application.addTest("Figure Joe 2", selectTestFigure2OptionListener);
	}

	/**
	 * Setup test using driver commands in context.
	 *
	 * @param application Application context.
	 */
	private static void setupCommandTests(Application application) {
		application.addTest("Load secret command", new SelectLoadSecretCommandOptionListener());
		application.addTest("Load test command",new SelectLoadTestCommandOptionListener());
		application.addTest("Load macro", new SelectLoadMacroOptionListener(MacroFeature.getMacroDriver(), CommandsFeature.getDriverCommandManager()));
		application.addTest("Clear macro", new SelectClearMacroListener(MacroFeature.getMacroDriver()));

		RectangleCanvas A4 = new RectangleCanvas(210,297);
		RectangleCanvas A7 = new RectangleCanvas(74,105);
		application.addTest("Canvas checker A4", new SelectCommandVisitorCanvasListener(DriverFeature.getDriverManager(),A4));
		application.addTest("Canvas checker A7", new SelectCommandVisitorCanvasListener(DriverFeature.getDriverManager(),A7));

		application.addTest("Run command", new SelectRunCurrentCommandOptionListener(DriverFeature.getDriverManager()));

		application.addTest("DriverCommandVisitorTest", new SelectCommandVisitorCounterListener(DriverFeature.getDriverManager()));
		application.addTest("ICompoundCommandVisitorTest", new SelectICompoundCommandVistorTestListener());

	}

	/**
	 * Setup driver manager, and set default Job2dDriver for application.
	 *
	 * @param application Application context.
	 */
	private static void setupDrivers(Application application) {
		Job2dDriver loggerDriver = new LoggerDriver();
		DriverFeature.addDriver("Logger driver", loggerDriver);
		
		AdditionalDriverFeature.addDriver("Logger driver", loggerDriver);
		AdditionalDriverFeature.addDriver("Macro recording", MacroFeature.getMacroDriver());

		DrawPanelController drawerController = DrawerFeature.getDrawerController();
		Job2dDriver driver = new LineDriverAdapter(drawerController, LineFactory.getBasicLine(), "basic");
		DriverFeature.addDriver("Line Simulator", driver);
		DriverFeature.getDriverManager().setCurrentDriver(driver);

		driver = new LineDriverAdapter(drawerController, LineFactory.getSpecialLine(), "special");
		DriverFeature.addDriver("Special line Simulator", driver);

		DriverFeature.addDriver("Macro recording", MacroFeature.getMacroDriver());

		TransformationDriver scaleTransformationDriver = new TransformationDriver(
				new LineDriverAdapter(drawerController, LineFactory.getBasicLine(), "basic line")
		);
		scaleTransformationDriver.addNewTransformation(new Scale(0.5d, 1.5d));
		DriverFeature.addDriver("Scale", scaleTransformationDriver);

		TransformationDriver rotateTransformationDriver = new TransformationDriver(
				new LineDriverAdapter(drawerController, LineFactory.getBasicLine(), "basic line")
		);
		rotateTransformationDriver.addNewTransformation(new Rotate(45.0d));
		DriverFeature.addDriver("Rotate", rotateTransformationDriver);

		DriverFeature.updateDriverInfo();

		DrawerPanelClickMouseListenerFeature drawerPanelClickMouseListenerFeature = new DrawerPanelClickMouseListenerFeature(
				application.getFreePanel(), DriverFeature.getDriverManager());

		application.getFreePanel().addMouseListener(drawerPanelClickMouseListenerFeature);

		Job2dDriver drawerOdometerFeature = new DrawerOdometerFeature();
		AdditionalDriverFeature.addDriver("Odometer", drawerOdometerFeature);

	}

	private static void setupWindows(Application application) {

		CommandManagerWindow commandManager = new CommandManagerWindow(CommandsFeature.getDriverCommandManager());
		application.addWindowComponent("Command Manager", commandManager);

		CommandManagerWindowCommandChangeObserver windowObserver = new CommandManagerWindowCommandChangeObserver(
				commandManager);
		CommandsFeature.getDriverCommandManager().getChangePublisher().addSubscriber(windowObserver);

	}

	/**
	 * Setup menu for adjusting logging settings.
	 *
	 * @param application Application context.
	 */
	private static void setupLogger(Application application) {

		application.addComponentMenu(Logger.class, "Logger", 0);
		application.addComponentMenuElement(Logger.class, "Clear log",
				(ActionEvent e) -> application.flushLoggerOutput());
		application.addComponentMenuElement(Logger.class, "Fine level", (ActionEvent e) -> logger.setLevel(Level.FINE));
		application.addComponentMenuElement(Logger.class, "Info level", (ActionEvent e) -> logger.setLevel(Level.INFO));
		application.addComponentMenuElement(Logger.class, "Warning level",
				(ActionEvent e) -> logger.setLevel(Level.WARNING));
		application.addComponentMenuElement(Logger.class, "Severe level",
				(ActionEvent e) -> logger.setLevel(Level.SEVERE));
		application.addComponentMenuElement(Logger.class, "OFF logging", (ActionEvent e) -> logger.setLevel(Level.OFF));
	}

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				Application app = new Application("Jobs 2D");
				DrawerFeature.setupDrawerPlugin(app);
				CommandsFeature.setupCommandManager();
				MacroFeature.setupMacro();

				DriverFeature.setupDriverPlugin(app);
				AdditionalDriverFeature.setupDriverPlugin(app);
				setupDrivers(app);
				setupPresetTests(app);
				setupCommandTests(app);
				setupLogger(app);
				setupWindows(app);
				app.setVisibility(true);
			}
		});
	}

}
