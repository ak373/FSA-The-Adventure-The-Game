;;; Sierra Script 1.0 - (do not remove this comment)
(script# 118)
(include sci.sh)
(include game.sh)
(include 118.shm)
(include 118.shp)
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
	rm118 0
)

(instance rm118 of Room
	(properties
		picture scriptNumber
		north 0
		east 0
		south 0
		west 0
		noun N_ROOM
        style (| dpOPEN_TOP dpANIMATION_BLACKOUT)
	)
	
	(method (init)
		(AddPolygonsToRoom @P_Default118)
		(super init:)
		(self setScript: RoomScript)
		(SetUpEgo -1 2) ; 3 is the loop of the ego where he's facing up.
		(gEgo posn: 190 145) ; this is the position of the ego
		(gEgo init:)
	)
)

(instance RoomScript of Script
	(properties)
	
	(method (doit)
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
            	(Prints {The exceptionally wide range of your friends and allies have all gathered to celebrate your victory.})
            	(Prints {"Welcooome baaaaaack! You were so braaaave! You've saved us aaaaaall!"})
            	(Prints {The room is filled with cheers.})
            	(Prints {"While we are all so grateful for everything you've done, I dare say I have but one more request on behalf of my kingdom."})
            	(Prints {"Would you please participate in a 30 minute retrospective?"})
            	(Die)
			)
		)
	)
)