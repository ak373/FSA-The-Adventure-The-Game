;;; Sierra Script 1.0 - (do not remove this comment)
(script# 114)
(include sci.sh)
(include game.sh)
(include 114.shm)
(include 114.shp)
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
	rm114 0
)

(instance rm114 of Room
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
		(AddPolygonsToRoom @P_Default114)
		(super init:)
		(self setScript: RoomScript)
		(switch gPreviousRoomNumber
		    (113
		        (SetUpEgo -1 3) ; 3 is the loop of the ego where he's facing up.
		        (gEgo posn: 165 175) ; this is the position of the ego
		    )
		    (116
		        (SetUpEgo -1 1) ; 3 is the loop of the ego where he's facing up.
		        (gEgo posn: 280 137) ; this is the position of the ego
		    )
		)
		(gEgo init:)
		
		(statueOne
			setOnMeCheck: omcPOLYGON (CreateNewPolygon @P_StatueOne)
		    init:
		)
		(statueTwo
			setOnMeCheck: omcPOLYGON (CreateNewPolygon @P_StatueTwo)
		    init:
		)
		(statueThree
			setOnMeCheck: omcPOLYGON (CreateNewPolygon @P_StatueThree)
		    init:
		)
		(throneRoomCrest
			setOnMeCheck: omcPOLYGON (CreateNewPolygon @P_ThroneRoomCrest)
		    init:
		)
		(hotSeat
			setOnMeCheck: omcPOLYGON (CreateNewPolygon @P_HotSeat)
		    init:
		)
		(serena
			approachVerbs: V_TALK
			setOnMeCheck: omcPOLYGON (CreateNewPolygon @P_Serena)
		    init:
		)
	)
)

(instance RoomScript of Script
	(properties)
	
	(method (doit)
	    ; If the ego is on green
	    (if (& (gEgo onControl:) ctlGREEN)
	        (gRoom newRoom: 116)
	    )
	    (if (& (gEgo onControl:) ctlTEAL)
	        (gRoom newRoom: 113)
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
            	(if (not (Btest F_114_Started))
    				(Bset F_114_Started)
					(Prints {The Grand Hall is well lit and decorated with majestic statues. The roaring fire on the far wall makes the room both warm and inviting.})
        			(Prints {Something doesn't sit well with you regarding that door to the east, though.})
        			(Prints {The woman on the balcony greets you. She seems to occasionally sing her words.})
	                (Prints {"Welcoooome! It's so good to see yoooooou! My name is Serena and I'm your Dungeon Lead!"})
	                (Prints {"If there's ever anything you need please don't hesitate to slack me!"})
				else
					(Prints {Serena sings a greeting to you.})
				)
			)
		)
	)
)

;;;
;;; ROOM INTERACTIBLES
;;;

(instance serena of Feature
	(properties
		x 100
		y 73
		approachX 190
		approachY 145
		noun N_SERENA
	)
	(method (doVerb theVerb)
        (switch theVerb
            (V_TALK
            	(if (Btest F_BookDone)
	                (Prints {"I think you're ready! I don't know whyyyy but I can feel iiiiit!"})
	                (Prints {"Things will certainly be dangerous up ahead. If you haven't been 'saving' then I suggest you start!"})
				else
					(Prints {"That door to the east? Well I'm not quite sure what's down there. A pair of goons stand guard and won't let me through. I can't even go to save my own kingdom!"})
					(Prints {"You? No - I couldn't... I couldn't ask you to risk yourself to face what's beyond that door. I wouldn't dream of calling for such sacrifice!"})
					(Prints {"What's that? You didn't say you would?.. Nor ask about the door in the first place? Oh. Weeeeell that's the storyyyyyyy!"})
				)
            )
            (else
                (super doVerb: theVerb &rest)
            )
        )
    )
)
(instance statueOne of Feature
	(properties
		x 110
		y 118
		noun N_STATUEONE
	)
)
(instance statueTwo of Feature
	(properties
		x 261
		y 99
		noun N_STATUETWO
	)
)
(instance statueThree of Feature
	(properties
		x 312
		y 121
		noun N_STATUETHREE
	)
)
(instance throneRoomCrest of Feature
	(properties
		x 200
		y 30
		noun N_THRONEROOMCREST
	)
)
(instance hotSeat of Feature
	(properties
		x 208
		y 105
		noun N_HOTSEAT
	)
)