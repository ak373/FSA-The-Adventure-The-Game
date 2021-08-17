;;; Sierra Script 1.0 - (do not remove this comment)
(script# 113)
(include sci.sh)
(include game.sh)
(include 113.shm)
(include 113.shp)
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
	rm113 0
)

(instance rm113 of Room
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
		(AddPolygonsToRoom @P_Default113)
		(super init:)
		(self setScript: RoomScript)
		(switch gPreviousRoomNumber
		    (111
		        (SetUpEgo -1 2) ; 3 is the loop of the ego where he's facing up.
		        (gEgo posn: 45 165) ; this is the position of the ego
		    )
		    (114
		        (SetUpEgo -1 1) ; 3 is the loop of the ego where he's facing up.
		        (gEgo posn: 182 127) ; this is the position of the ego
		    )
		)
		(gEgo init:)
		
		(entranceGargOne
			setOnMeCheck: omcPOLYGON (CreateNewPolygon @P_EntranceGargOne)
		    init:
		)
		(entranceGargTwo
			setOnMeCheck: omcPOLYGON (CreateNewPolygon @P_EntranceGargTwo)
		    init:
		)
	)
)

(instance RoomScript of Script
	(properties)
	
	(method (doit)
	    ; If the ego is on green
	    (if (& (gEgo onControl:) ctlGREEN)
	        (gRoom newRoom: 114)
	    )
	    (if (& (gEgo onControl:) ctlTEAL)
	        (gRoom newRoom: 111)
	    )
	    (if (& (gEgo onControl:) ctlMAROON)
        	(Prints {Hey - you found my secret spot! Now shoo.})
        	(gEgo posn: 75 160)
        	(if (not (Btest F_SecretSpot))
        		(Bset F_SecretSpot)
	        	(AddToScore 1)
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
        		(if (not (Btest F_113_Started))
    				(Bset F_113_Started)
    				(Prints {You've come to the entrance of a grand structure; you'd say it looks like a Palace if that didn't make you feel dorky. Within, you can make out the glow of a fire in the distance.})	
				)
			)
		)
	)
)

;;;
;;; ROOM INTERACTIBLES
;;;


(instance entranceGargOne of Feature
	(properties
		x 48
		y 23
		noun N_ENTRANCEGARGONE
	)
)
(instance entranceGargTwo of Feature
	(properties
		x 300
		y 20
		noun N_ENTRANCEGARGTWO
	)
)