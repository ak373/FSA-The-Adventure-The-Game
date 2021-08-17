;;; Sierra Script 1.0 - (do not remove this comment)
(script# 112)
(include sci.sh)
(include game.sh)
(include 112.shm)
(include 112.shp)
(use Actor)
(use Cycle)
(use Door)
(use Feature)
(use Game)
(use Main)
(use Polygon)
(use System)
(use Print)

(public
	rm112 0
)

(instance rm112 of Room
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
		(AddPolygonsToRoom @P_Default112)
		(super init:)
		(self setScript: RoomScript)
		(switch gPreviousRoomNumber
		    (111
		        (SetUpEgo -1 2) ; 3 is the loop of the ego where he's facing up.
		        (gEgo posn: 285 145) ; this is the position of the ego
		    )
		    (115
		        (SetUpEgo -1 4) ; 3 is the loop of the ego where he's facing up.
		        (gEgo posn: 148 142) ; this is the position of the ego
		    )
			;(else 
			;	; Set up ego view and loop (direction)
			;	(SetUpEgo -1 0)
			;	(gEgo posn: 150 100)
			;)
		)
		(gEgo init:)
		
		(chair
			setOnMeCheck: omcPOLYGON (CreateNewPolygon @P_Chair)
		    init:
		)
		(wizardDoor
			setOnMeCheck: omcPOLYGON (CreateNewPolygon @P_WizardDoor)
		    init:
		)
		(sarah
			approachVerbs: V_TALK
			approachVerbs: V_MUSTARD
			setOnMeCheck: omcPOLYGON (CreateNewPolygon @P_Sarah)
		    init:
		)
	)
)

(instance RoomScript of Script
	(properties)
	
	(method (doit)
	    ; If the ego is on green
	    (if (& (gEgo onControl:) ctlGREEN)
	        (gRoom newRoom: 111)
	    )
	    (if (& (gEgo onControl:) ctlTEAL)
	    	(Prints {As you approach the door, a strange sensation fills your body...})
	        (gRoom newRoom: 115)
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
        		(if (not (Btest F_112_Started))
    				(Bset F_112_Started)
    				(Prints {You've entered a large, ornate room. On the far end stands a woman (and the apparent source of the music). You're not quite sure what she's wearing on her head, but your best guess is... a pizza hat?})
    				(Prints {Or... a party hat? A pizza/party hat?})
    				(Prints {I get it. Do you?})
    				(Prints {Anyway. Back to it.})
    				(Prints {The woman lowers her music and turns to greet you.})
    				(Prints {"Hello and welcome! You must be the newest recruit! My name is Sarah, and we're all very excited to have you here. Who or where is everyone? I'm not sure! But they're around."})
				else
					(Prints {Sarah pauses her grooving long enough to greet you, and then gets back to it.})
				)
			)
		)
	)
)

;;;
;;; ROOM INTERACTIBLES
;;;

(instance sarah of Feature
	(properties
		x 36
		y 140
		approachX 61
		approachY 177
		noun N_SARAH
	)
	(method (doVerb theVerb)
        (switch theVerb
            (V_TALK
            	(if (Btest F_MustardDone)
	                (Prints {"Hello! I hope you're finding your way. If ever in doubt: What Would Dumbledore Do?"})
	                (Prints {(WWDD)})
				else
					(Prints {"Hey! Let me know if you need anything: I'm here for you!"})
					(Prints {"Maybe some dim sum? Or, hm. A banana? Not sure why I have this. I can feel its potential though."})
				)
            )
            (V_MUSTARD
            	(Bset F_MustardDone)
				(gEgo get: INV_BOOK)
            	(gEgo put: INV_MUSTARD)
            	(Prints {You hand Sarah the packet of mustard.})
				(Prints {"Oh, wow! This is perfect! I'm gonna put it on my dim sum!"})
				(Prints {"Here! Please, take this! It's my most favorite thing in the whole world and it would mean everything to me for you to have it!"})
				(Prints {You receive the complete Harry Potter: Penultimate Edition book set.})
			)
            (else
                (super doVerb: theVerb &rest)
            )
        )
    )
)
(instance chair of Feature
	(properties
		x 183
		y 178
		noun N_CHAIR
	)
)
(instance wizardDoor of Feature
	(properties
		x 147
		y 108
		noun N_WIZARDDOOR
	)
)