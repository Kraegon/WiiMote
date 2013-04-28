package input;

import control.GameLogic;
import control.ViewLogic;
import wiiusej.WiiUseApiManager;
import wiiusej.Wiimote;
import wiiusej.wiiusejevents.physicalevents.ExpansionEvent;
import wiiusej.wiiusejevents.physicalevents.IREvent;
import wiiusej.wiiusejevents.physicalevents.MotionSensingEvent;
import wiiusej.wiiusejevents.physicalevents.WiimoteButtonsEvent;
import wiiusej.wiiusejevents.utils.WiimoteListener;
import wiiusej.wiiusejevents.wiiuseapievents.ClassicControllerInsertedEvent;
import wiiusej.wiiusejevents.wiiuseapievents.ClassicControllerRemovedEvent;
import wiiusej.wiiusejevents.wiiuseapievents.DisconnectionEvent;
import wiiusej.wiiusejevents.wiiuseapievents.GuitarHeroInsertedEvent;
import wiiusej.wiiusejevents.wiiuseapievents.GuitarHeroRemovedEvent;
import wiiusej.wiiusejevents.wiiuseapievents.NunchukInsertedEvent;
import wiiusej.wiiusejevents.wiiuseapievents.NunchukRemovedEvent;
import wiiusej.wiiusejevents.wiiuseapievents.StatusEvent;

public class Controller {
	private Wiimote remote;
	private static Controller INSTANCE;
	private boolean direction;
	private boolean aHeld;

	public Controller() {
		direction = true;
		aHeld = false;
		init();
	}

	public void init() {
		Wiimote[] wiimotes = WiiUseApiManager.getWiimotes(1, true);
		remote = wiimotes[0];
		remote.activateIRTRacking();
		remote.activateMotionSensing();
		remote.addWiiMoteEventListeners(new WiimoteListener() {
			// REDUNDANT
			public void onStatusEvent(StatusEvent arg0) {
			}

			public void onNunchukRemovedEvent(NunchukRemovedEvent arg0) {
			}

			public void onNunchukInsertedEvent(NunchukInsertedEvent arg0) {
			}

			public void onMotionSensingEvent(MotionSensingEvent arg0) {
			}

			public void onIrEvent(IREvent arg0) {
			}

			public void onGuitarHeroRemovedEvent(GuitarHeroRemovedEvent arg0) {
			}

			public void onGuitarHeroInsertedEvent(GuitarHeroInsertedEvent arg0) {
			}

			public void onExpansionEvent(ExpansionEvent arg0) {
			}

			public void onDisconnectionEvent(DisconnectionEvent arg0) {
			}

			public void onClassicControllerRemovedEvent(
					ClassicControllerRemovedEvent arg0) {
			}

			public void onClassicControllerInsertedEvent(
					ClassicControllerInsertedEvent arg0) {
			}

			// NOT REDUNDANT
			public void onButtonsEvent(WiimoteButtonsEvent arg0) {
				if (arg0.isButtonUpJustReleased() && !GameLogic.getInstance().isDisplaying()) {
					ViewLogic.getInstance().selectUpwards();
				}	
				if (arg0.isButtonDownJustReleased() && !GameLogic.getInstance().isDisplaying()) {
					ViewLogic.getInstance().selectDownwards();
				}
				if (arg0.isButtonLeftJustReleased() && !GameLogic.getInstance().isDisplaying()) {
					ViewLogic.getInstance().selectLeft();
				}
				if (arg0.isButtonRightJustReleased() && !GameLogic.getInstance().isDisplaying()) {
					ViewLogic.getInstance().selectRight();
				}
				if (arg0.isButtonOneJustReleased() && !GameLogic.getInstance().isPlaying()) {
					GameLogic.getInstance().generateNext();
					GameLogic.getInstance().highlightSequence();
					GameLogic.getInstance().togglePlaying();
				}
				if (arg0.isButtonAJustReleased()){
					if(GameLogic.getInstance().isPlaying()){
						boolean isCorrect = GameLogic.getInstance().checkInput();
						if(isCorrect){
							if(GameLogic.getInstance().getcurrentPoint() == 0){
								GameLogic.getInstance().generateNext();
								GameLogic.getInstance().highlightSequence();
							}
						} else {
							remote.activateRumble();
							
							try {
								Thread.sleep(1500);
							} catch (InterruptedException e) {}
							remote.deactivateRumble();
							GameLogic.getInstance().reset();
							GameLogic.getInstance().togglePlaying();
						}
					}
				}
				if (arg0.isButtonAHeld() && arg0.isButtonBHeld()){
					System.exit(0);
				}
				if (arg0.isButtonAHeld())
					aHeld = true;
				else
					aHeld = false;
			}
		});
	}
	public void snake() throws InterruptedException{
			remote.setLeds(true, false, false, false);
			Thread.sleep(200);
			remote.setLeds(true, true, false, false);	
			Thread.sleep(200);
			remote.setLeds(false, true, false, false);	
			Thread.sleep(200);
			remote.setLeds(false, true, true, false);	
			Thread.sleep(200);
			remote.setLeds(false, false, true, false);
			Thread.sleep(200);
			remote.setLeds(false, false, true, true);	
			Thread.sleep(200);
			remote.setLeds(false, false, false, true);
			Thread.sleep(200);
			remote.setLeds(true, false, false, true);
			Thread.sleep(200);
	}
	public static Controller getInstance(){
		if(INSTANCE == null)
			INSTANCE = new Controller();
		return INSTANCE;
	}
	
	public boolean isAHeld(){
		return aHeld;
	}
	
	public void toggleDirection() {
		direction = !direction;
	}

	public boolean direction() {
		return direction;
	}

	public Wiimote getRemote() {
		return remote;
	}

}
