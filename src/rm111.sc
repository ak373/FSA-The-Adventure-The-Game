;;; Sierra Script 1.0 - (do not remove this comment)
(script# 111)
(include sci.sh)
(include game.sh)
(include 111.shm)
(include 111.shp)
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
	rm111 0
)

(instance rm111 of Room
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
		(AddPolygonsToRoom @P_Default111)
		(super init:)
		(self setScript: RoomScript)
		(switch gPreviousRoomNumber
		    (110
		        (SetUpEgo -1 2) ; 3 is the loop of the ego where he's facing up.
		        (gEgo posn: 277 174) ; this is the position of the ego
		    )
		    (112
		        (SetUpEgo -1 4) ; 3 is the loop of the ego where he's facing up.
		        (gEgo posn: 20 120) ; this is the position of the ego
		    )
		    (113
		        (SetUpEgo -1 1) ; 3 is the loop of the ego where he's facing up.
		        (gEgo posn: 83 96) ; this is the position of the ego
		    )
			(else 
				; Set up ego view and loop (direction)
				(SetUpEgo -1 0)
				(gEgo posn: 150 100)
			)
		)
		(gEgo init:)
		
		(cabinetOne
			approachVerbs: V_DO
			setOnMeCheck: omcPOLYGON (CreateNewPolygon @P_CabinetOne)
		    init:
		)
		(cabinetTwo
			approachVerbs: V_DO
			setOnMeCheck: omcPOLYGON (CreateNewPolygon @P_CabinetTwo)
		    init:
		)
		(cabinetThree
			approachVerbs: V_DO
			setOnMeCheck: omcPOLYGON (CreateNewPolygon @P_CabinetThree)
		    init:
		)
		(cabinetFour
			approachVerbs: V_DO
			setOnMeCheck: omcPOLYGON (CreateNewPolygon @P_CabinetFour)
		    init:
		)
		(cabinetFive
			approachVerbs: V_DO
			setOnMeCheck: omcPOLYGON (CreateNewPolygon @P_CabinetFive)
		    init:
		)
		(candle
			approachVerbs: V_DO
			setOnMeCheck: omcPOLYGON (CreateNewPolygon @P_Candle)
		    init:
		)
		(curtain
			setOnMeCheck: omcPOLYGON (CreateNewPolygon @P_Curtain)
		    init:
		)
	)
)

(instance RoomScript of Script
	(properties)
	
	(method (doit)
	    ; If the ego is on green
	    (if (& (gEgo onControl:) ctlGREEN)
	        (gRoom newRoom: 110) ; change the room to 110
	    )
	    (if (& (gEgo onControl:) ctlTEAL)
	        (gRoom newRoom: 113)
	    )
	    (if (& (gEgo onControl:) ctlMAROON)
	        (gRoom newRoom: 112)
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
    			(if (not (Btest F_111_Started))
    				(Bset F_111_Started)
    				(Prints {This room is certainly far less dreary. A large, dusty cabinet stands beside two open doorways. You hear the faint sound of music coming from the west.})				
				)	
			)
		)
	)
)

;;;
;;; ROOM INTERACTIBLES
;;;

(instance cabinetOne of Feature
	(properties
		x 150
		y 115
		approachX 150
		approachY 130
		noun N_CABINETONE
	)
	(method (doVerb theVerb)
        (switch theVerb
            (V_DO
            	(if (not (Btest F_CoinLooted))
            		(Bset F_CoinLooted)
	                (Prints {In the back of the dusty cabinet, you find some loose change. Nice! Just imagine how much candy you could buy with that.})
					(gEgo get: INV_COIN)
					(gEgo get: INV_COIN)
					(gEgo get: INV_COIN)
					(Prints {And - if you're lucky - I'll even have time actually to institute a barter system in this game.})
				else
					(Prints {What? You want more money you can't use? Here! Have it! Have it all!})
					(gEgo get: INV_COIN)
					(gEgo get: INV_COIN)
					(gEgo get: INV_COIN)
					(gEgo get: INV_COIN)
					(gEgo get: INV_COIN)
					(gEgo get: INV_COIN)
					(gEgo get: INV_COIN)
					(gEgo get: INV_COIN)
					(gEgo get: INV_COIN)
					(gEgo get: INV_COIN)
				)
            )
            (else
                (super doVerb: theVerb &rest)
            )
        )
    )
)
(instance cabinetTwo of Feature
	(properties
		x 178
		y 115
		approachX 178
		approachY 130
		noun N_CABINETTWO
	)
)
(instance cabinetThree of Feature
	(properties
		x 204
		y 115
		approachX 204
		approachY 130
		noun N_CABINETTHREE
	)
)
(instance cabinetFour of Feature
	(properties
		x 157
		y 66
		approachX 157
		approachY 130
		noun N_CABINETFOUR
	)
	(method (doVerb theVerb)
        (switch theVerb
            (V_DO
            	(if (not (Btest F_KnifeLooted))
            		(Bset F_KnifeLooted)
					(gEgo get: INV_KNIFE)
	                (Prints {There's a knife tucked away in a dusty corner. That's sure to come in handy.})
				)
            )
            (else
                (super doVerb: theVerb &rest)
            )
        )
    )
)
(instance cabinetFive of Feature
	(properties
		x 195
		y 66
		approachX 195
		approachY 130
		noun N_CABINETFIVE
	)
)
(instance candle of Feature
	(properties
		x 107
		y 27
		approachX 99
		approachY 96
		noun N_CANDLE
	)
)
(instance curtain of Feature
	(properties
		x 292
		y 131
		noun N_CURTAIN
	)
)