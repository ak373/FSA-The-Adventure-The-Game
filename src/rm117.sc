;;; Sierra Script 1.0 - (do not remove this comment)
(script# 117)
(include sci.sh)
(include game.sh)
(include 117.shm)
(include 117.shp)
(use Actor)
(use Cycle)
(use Door)
(use Feature)
(use Game)
(use Main)
(use Polygon)
(use System)
(use Print)
(use Wander)
(use PFollow)
(use StopWalk)

(public
	rm117 0
)

(instance rm117 of Room
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
		(AddPolygonsToRoom @P_Default117)
		(super init:)
		(self setScript: RoomScript)
		(switch gPreviousRoomNumber
			(116
		        (SetUpEgo -1 1) ; 3 is the loop of the ego where he's facing up.
		        (gEgo posn: 11 162) ; this is the position of the ego
		    )
			(else 
				; Set up ego view and loop (direction)
				(SetUpEgo -1 0)
				(gEgo posn: 150 100)
			)
		)
		(gEgo init:)
		(longWayDown
			setOnMeCheck: omcPOLYGON (CreateNewPolygon @P_LongWayDown)
		    init:
		)
		(evilEgo
		    init:
		    setCycle: StopWalk -1
		)
		(evilEgo hide:)
	)
)

(instance RoomScript of Script
	(properties)
	
	(method (doit)
		(if (& (evilEgo onControl:) ctlMAROON)
			(Prints {Displaying your algorithmic mastery, you fool your following foe to face a fantastic fall.})
	        (gGame handsOff:)
	        (gRoom newRoom: 118)
		)
			
	    (if (& (gEgo onControl:) ctlGREEN)
			(Prints {You drop into an athletic stance, and spring across the chasm with what would be the most masterful leap ever to be witnessed by mankind.})
			(Prints {So masterful that we cannot do it justice here, so we'll just show you as moved across.})
	        (gEgo posn: 175 145)
	        
	        (if (not (Btest F_FinalEvent))
	        	(Bset F_FinalEvent)
	        	(gGame handsOff:)
	        	(evilEgo show:)
	        	(= seconds 2)
	        	(Prints {"I am you. I am the part of you that refuses to face what is to come. I am the part that tells you to surrender. What makes you give up on what you know is best."})
	    		(Prints {"I am anxiety. I am fear. I am failure."})
	    		(Prints {"Face me, or retreat to the familiar."})
	        	(gGame handsOn:)
			)
	    )
	    (if (& (gEgo onControl:) ctlTEAL)
			(Prints {You drop into an athletic stance, and spring across the chasm with what would be the most masterful leap ever to be witnessed by mankind.})
			(Prints {So masterful that we cannot do it justice here, so we'll just show you as moved across.})
	        (gEgo posn: 75 170)
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
            	(Prints {There's certainly something... demonic about this place. You're not sure where you are or how you got there, but this is it.})
        		(Prints {It's nice though, isn't it? Kinda place where you can raise your kids.})
			)
		)
	)
)

;;;
;;; ROOM INTERACTIBLES
;;;

(instance longWayDown of Feature
	(properties
		x 77
		y 31
		noun N_LONGWAYDOWN
	)
)

(instance evilEgo of Actor
	(properties
		view 1
		x 259
		y 91
		signal ignAct
		noun N_EVILEGO
		moveSpeed 15
		cycleSpeed 15
	)
	(method (doVerb theVerb)
        (switch theVerb
            (V_AXE
            	(if (Btest F_FinalEvent)
					(gEgo put: INV_AXE)
	            	(Prints {Bravely, you lumber the mighty goon's axe into the air.})
	            	(Prints {"Brawn stands no chance against the mind. Only your guile can save you."})
	            	(Prints {With a wave of his hand, your axe flies loose and into the abyss.})
	            	(Prints {"Have you learned enough? Outwit my programming, lest you shall die."})
					(evilEgo setMotion: PFollow gEgo 10)
				)
            )
            (else
                (super doVerb: theVerb &rest)
            )
        )
    )

    (method (init)
        (super init: &rest)
        (self setScript: evilEgoScript)
    )
)

(instance evilEgoScript of Script
    (properties)

    (method (doit)
        (super doit:)
        (if (<= (client distanceTo: gEgo) 15)
        	(Die)
    	)
	)
)