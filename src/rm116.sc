;;; Sierra Script 1.0 - (do not remove this comment)
(script# 116)
(include sci.sh)
(include game.sh)
(include 116.shm)
(include 116.shp)
(use Actor)
(use Cycle)
(use Door)
(use Feature)
(use Game)
(use Main)
(use Polygon)
(use System)
(use Print)
(use Jump)

(public
	rm116 0
)

(instance rm116 of Room
	(properties
		picture scriptNumber
		north 0
		east 0
		south 0
		west 0
		noun N_ROOM
		style (| dpANIMATION_BLACKOUT dpOPEN_FADEPALETTE)
	)
	
	(method (init)
		(AddPolygonsToRoom @P_Default116)
		(super init:)
		(self setScript: RoomScript)
		(switch gPreviousRoomNumber
		    (114
		        (SetUpEgo -1 1) ; 3 is the loop of the ego where he's facing up.
		        (gEgo posn: 80 38) ; this is the position of the ego
		    )
		)
		(gEgo init:)
		
		(theBox
			init:
		)
		(theBox hide:)
		
		(goons
			approachVerbs: V_BOX
			setOnMeCheck: omcPOLYGON (CreateNewPolygon @P_Goons)
		    init:
		)
	)
)

(local
    talkOne = FALSE
)
(local
    talkTwo = FALSE
)
(local
    talkThree = FALSE
)

(instance RoomScript of Script
	(properties)
	
	(method (doit)
	    ; If the ego is on green
	    (if (& (gEgo onControl:) ctlGREEN)
	        (gRoom newRoom: 114)
	    )
	    (if (& (gEgo onControl:) ctlTEAL)
	        (Die)
	    )
	    (if (& (gEgo onControl:) ctlMAROON)
	        (if (not talkOne)
	        	(= talkOne TRUE)
	        	(Prints {"What're we doin' here, anyways?"})
	        	(Prints {"Watchin' da door like master said, of course!"})
	        	(Prints {"Yeah! But why?"})
	        	(Prints {"Huh? Whaddya mean?"})
	        	(Prints {"Why are we watchin' a door?"})
	        	(Prints {"Huh. Never thought about that. Maybe it'll disappear if we don't? Kind of like 'if a tree falls in a forest?' sort of thing?"})
	        	(Prints {"Chrodinger's Cat, even?"})
	        	(Prints {"Ah, yes! Somewhere in between the two."})
	        	(Prints {"Duhhhh. Awright!"})
			)
	    )
	    (if (& (gEgo onControl:) ctlPURPLE)
	        (if (not talkTwo)
	        	(= talkTwo TRUE)
	        	(Prints {"Da Master says we're his 'windmill.' Whaddya think that means?"})
	        	(Prints {"Duh, stupid! We're 'windmills' because we blow hot wind!"})
	        	(Prints {"Is that what they do?"})
	        	(Prints {"Huh? Whaddya mean?"})
	        	(Prints {"Is the air they blow hot?"})
	        	(Prints {"Of course, dummy! How else would they reach 88 MPH?"})
	        	(Prints {"Oh. Right!"})
			)
	    )
	    (if (& (gEgo onControl:) ctlBROWN)
	        (if (not talkThree)
	        	(= talkThree TRUE)
	        	(Prints {"See this axe?"})
	        	(Prints {"Of course, dummy!"})
	        	(Prints {"It's real."})
	        	(Prints {"Huh? Whaddya mean?"})
	        	(Prints {"If I ever see anyone round that corner I'm gonna slice 'em good."})
	        	(Prints {"Me too! Swish Swash!"})
	        	(Prints {"Swish swash! Swish swash!"})
			)
	    )
		(super doit:)
		; code executed each game cycle
	)
	
	(method (changeState newState)
		(= state newState)
		(switch state
            (0
                    (= seconds 1)
            )
            (1
            	(if (not (Btest F_116_Started))
    				(Bset F_116_Started)
					(Prints {A couple of goons stand dim-wittedly below.})
        			(Prints {You have a feeling in the pit of your stomach that once you are through that door there will be no going back.})
				else
					(Prints {The goons grumble at and converse with each other.})
					(Prints {You have a feeling in the pit of your stomach that once you are through that door there will be no going back.})
				)
			)
		)
	)
)

;;;
;;; ROOM INTERACTIBLES
;;;

(instance goons of Feature
	(properties
		x 272
		y 150
		approachX 76
		approachY 38
		noun N_GOONS
	)
	(method (doVerb theVerb)
        (switch theVerb
            (V_BOX
            	(Prints {Whether it's what you intended or not -- this is happening.})
				(gEgo put: INV_BOX)
            	(Prints {You throw the box down the stairs.})
	            ;(self setScript: hurlBoxScript)
	            (Prints {The box slid down the stairs with such velocity that it crushed both the goons and the door behind them. It's a horrible, gory sight, and we in the production team choose to spare you from seeing it. All we can do is hope this text box is big enough to block it.})
	        	(Prints {You pick up one of the goon's axes and head into the final room.})
				(gEgo get: INV_AXE)
	            (gRoom newRoom: 117)
	            (gGame handsOn:)
            )
            (else
                (super doVerb: theVerb &rest)
            )
        )
    )
)

(instance theBox of Actor
	(properties
		view 123
		x 150
		y 100
		signal ignAct
		loop 1
		cel 0
		noun N_BOX
		priority 0
		approachX 130
		approachY 110
		cycleSpeed 15
	)
)



(instance hurlBoxScript of Script
	(properties)
	(method (changeState newState)
	 (= state newState)
	    (switchto state
	        (
	            (gGame handsOff:)
	            ; We could also say (theBox setCycle: EndLoop self)
	            (theBox
                        posn: (gEgo x?) (gEgo y?) 30
                        show:
                        setMotion: JumpTo 51 61 self
                )	            
	        )
	        (
	        	(theBox setCycle: EndLoop self)
	        	(= seconds 4)
			)
	        (
	            (theBox
                        setMotion: JumpTo 72 126 self
                )
                (Prints {"Duhhh. What's that sound?"})
	        )
	        (
	            (theBox
                        setMotion: JumpTo 134 165 self
                )
                (Prints {"Hummm. I dunno."})
	        )
	        (
	            (theBox
                        setMotion: JumpTo 251 185 self
                )
                (Prints {"Uh oh."})
                (Prints {"I love you, buddy!"})
	        	(Prints {"Huh? Whaddya mean?"})
	        )
	        (
	            (theBox
                        setMotion: JumpTo 275 160 self
                )
	        )
	        (
	            (= seconds 2)
	        )
	        (
	            (Prints {The box slid down the stairs with such velocity that it crushed both the goons and the door behind them. It's a horrible, gory sight, and we in the production team choose to spare you from seeing it. All we can do is hope this text box is big enough to block it.})
	        	(Prints {You pick up one of the goon's axes and head into the final room.})
				(gEgo get: INV_AXE)
	            (gRoom newRoom: 117)
	            (gGame handsOn:)
	        )
	    )
	)
)