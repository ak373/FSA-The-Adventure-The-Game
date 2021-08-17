;;; Sierra Script 1.0 - (do not remove this comment)
(script# 110)
(include sci.sh)
(include Verbs.sh)
(include game.sh)
(include 110.shm)
(include 110.shp)
(use Main)
(use DisposeLoad)
(use Sound)
(use Cycle)
(use Game)
(use Actor)
(use System)
(use Print)
(use Polygon)
(use Feature)
(use StopWalk)
(use Wander)
(use PFollow)
(use Jump)

(public
	rm110 0
)

(instance rm110 of Room
	(properties
		picture 110
		style (| dpANIMATION_BLACKOUT dpOPEN_FADEPALETTE)
		horizon 50
		vanishingX 130
		vanishingY 50
		noun N_ROOM
	)
	
	(method (init)
		(AddPolygonsToRoom @P_Default110)
		(super init:)
		(self setScript: RoomScript)
		(switch gPreviousRoomNumber
			; Add room numbers here to set up the ego when coming from different directions.
			 (111
		        (SetUpEgo -1 4) ; 3 is the loop of the ego where he's facing up.
		        (gEgo posn: 59 126) ; this is the position of the ego
		    )
			(else 
				(SetUpEgo -1 2)
				(gEgo posn: 275 150)
			)
		)
		(gEgo init:)
		; We just came from the title screen, so we need to call this to give control
		; to the player.
		(gGame handsOn:)
;		(if (not (gEgo has: INV_BOX))
;	   		 ; initialize it here (if the player doesn't have it)
;	    	(theBox
;				approachVerbs: V_DO
;				init:
;			)
;			(if (Btest F_ShrunkBox)
;			    ; Set the box to the last animation cel
;			    (theBox setCel: 1000)
;			)
;			(if (gEgo has: INV_BOX)
;			    ; hide it if the ego is carrying it
;			    (theBox hide:)
;			)
;		)
	
;		(redBox
;			approachVerbs: V_DO
;			init:
;		)
		(grate
			approachVerbs: V_DO
			setOnMeCheck: omcPOLYGON (CreateNewPolygon @P_Grate)
		    init:
		)
		(startWindow
			setOnMeCheck: omcPOLYGON (CreateNewPolygon @P_StartWindow)
		    init:
		)
		(reliefOne
			setOnMeCheck: omcPOLYGON (CreateNewPolygon @P_ReliefOne)
		    init:
		)
		(reliefTwo
			setOnMeCheck: omcPOLYGON (CreateNewPolygon @P_ReliefTwo)
		    init:
		)
		(reliefThree
			setOnMeCheck: omcPOLYGON (CreateNewPolygon @P_ReliefThree)
		    init:
		)
		(reliefFour
			setOnMeCheck: omcPOLYGON (CreateNewPolygon @P_ReliefFour)
		    init:
		)
		(grimReaperOne
			setOnMeCheck: omcPOLYGON (CreateNewPolygon @P_GrimReaperOne)
		    init:
		)
		(grimReaperTwo
			setOnMeCheck: omcPOLYGON (CreateNewPolygon @P_GrimReaperTwo)
		    init:
		)
		(grimHand
			approachVerbs: V_DO
			setOnMeCheck: omcPOLYGON (CreateNewPolygon @P_GrimHand)
		    init:
		)
;		(evilEgo
;		    init:
;		    setCycle: StopWalk -1
;		    ;setMotion: PFollow gEgo 20
;		)
	)
)

(instance RoomScript of Script
	(properties)
	
	(method (changeState newState)
		(= state newState)
		(switch state
            (0
                    (= seconds 2)
            )
            (1
        		(if (not (Btest F_110_Started))
        			(Bset F_110_Started)
        			(Prints {You've been thrown into the deep end of what you are hoping will be a life changing process. The trials will be great, you are told, and the outcome just as rewarding. Not knowing what to expect, you set out on your journey...})      
				else
					(Prints {This room holds the title of "least welcoming welcome room" ever.})
				)
			)
		)
	)
	
	(method (doit &tmp x y)
   		; If the ego is on green
    	(if (& (gEgo onControl:) ctlGREEN)
        	(gRoom newRoom: 111) ; change the room to 111
		)
    	(super doit:)
    	; Grab the ego's x and y and put them in temp variables
    	; (= x (gEgo x?))
    	; (= y (gEgo y?))

    	; If the ego is past 150
    	;(if (> x 150)
        	; Then put him back at 20
        ;	(gEgo posn: 20 y)
    	)
	)


;(instance theBox of Actor
;	(properties
;		view 123
;		x 150
;		y 100
;		signal ignAct
;		loop 1
;		cel 0
;		noun N_BOX
;		priority 0
;		approachX 130
;		approachY 110
;		cycleSpeed 15
;	)
;
;	(method (doVerb theVerb)
;		(switch theVerb
 ;           (V_DO
  ;          (if (not (Btest F_ShrunkBox))
   ;             (self setScript: shrinkBoxScript)
	;            else
	 ;               (Prints {It's so cute! You just wanna put it in your li'l pocket!})
	  ;              (gEgo get: INV_BOX)
	   ;             (theBox hide:)
	    ;            (Prints {So you do.})
	     ;       )
          ;  )
			;(else 
;				(super doVerb: theVerb &rest)
;			)
;		)
;	)
;)

;(local
;	hasBoxShrunk = FALSE
;)
;(instance shrinkBoxScript of Script
;	(properties)
;	(method (changeState newState)
;	 (= state newState)
;	    (switchto state
;	        (
;	            (gGame handsOff:)
;	            ; We could also say (theBox setCycle: EndLoop self)
;	            (client setCycle: EndLoop self)
;	        )
;	        (
;	            (= seconds 2)
;	        )
;	        (
 ;  				(ScoreFlag F_ShrunkBox 3)
;	            (Prints {The box shrunk!})
;	            (gGame handsOn:)
;	        )
;	    )
;	)
;)


;(instance redBox of Prop
;	(properties
;		view 123
;		x 76
;		y 162
;		signal ignAct
;		loop 0
;		cel 1
;		noun N_REDBOX
;		priority -1
;		approachX 56
;		approachY 172
;	)
;
;	(method (doVerb theVerb)
;		(switch theVerb
 ;           (V_DO
  ;          (if (== 1 (self cel?))
   ;             (self setCel: 0)
    ;            (gMessager say: N_REDBOX V_DO 0 0)
     ;       else
      ;      	(self setCel: 1)
       ;         (gMessager say: N_REDBOX V_DO 1 0)
        ;    )
;        )
;			(else 
;				(super doVerb: theVerb &rest)
;			)
;		)
;	)
;)

;;;
;;; ROOM INTERACTIBLES
;;;

(instance grate of Feature
	(properties
		x 160
		y 180
		approachX 163
		approachY 163
		noun N_GRATE
	)
	(method (doVerb theVerb)
        (switch theVerb
            (V_DO
            	(if (not (Btest F_MustardLooted))
            		(Bset F_MustardLooted)
	                (Prints {Reaching your hand around in the muck, you find a grimy old mustard packet.})
					(gEgo get: INV_MUSTARD)
					(Prints {Hey - it's still sealed. You wipe it off and put it away.})
				else
					(Prints {You stick your hand back down into the grime and filth, hoping to find some more treasures.})
					(Prints {You do not.})
				)
            )
            (else
                (super doVerb: theVerb &rest)
            )
        )
    )
)
(instance startWindow of Feature
	(properties
		x 180
		y 30
		noun N_STARTWINDOW
	)
)
(instance reliefOne of Feature
	(properties
		x 105
		y 100
		noun N_RELIEFONE
	)
)
(instance reliefTwo of Feature
	(properties
		x 180
		y 100
		noun N_RELIEFTWO
	)
)
(instance reliefThree of Feature
	(properties
		x 236
		y 100
		noun N_RELIEFTHREE
	)
)
(instance reliefFour of Feature
	(properties
		x 285
		y 100
		noun N_RELIEFFOUR
	)
)
(instance grimReaperOne of Feature
	(properties
		x 30
		y 110
		noun N_GRIMREAPERONE
	)
)
(instance grimReaperTwo of Feature
	(properties
		x 318
		y 130
		noun N_GRIMREAPERTWO
	)
)
(instance grimHand of Feature
	(properties
		x 300
		y 152
		approachX 280
		approachY 180
		noun N_GRIMHAND
	)
)

;(instance evilEgo of Actor
;	(properties
;		view 1
;		x 231
;		y 131
;		signal ignAct
;		noun N_EVILEGO
;		moveSpeed 30
;		cycleSpeed 30
;	)

;    (method (init)
 ;       (super init: &rest)
  ;      (self setScript: evilEgoScript)
   ; )
	
;	(method (doVerb theVerb)
;	    (switch theVerb
;	        (V_BOX
 ;           	(self setScript: throwBoxScript)
;	        )
;	        (else
;	            (super doVerb: theVerb &rest)
;	        )
;	    )
;	)
;)

;(instance evilEgoScript of Script
 ;   (properties)

  ;  (method (doit)
   ;     (super doit:)
    ;    (if (<= (client distanceTo: gEgo) 25)
     ;       (Die)
    ;	)
	;)
;)

;(instance throwBoxScript of Script
 ;   (properties)
;
 ;   (method (changeState newState)
  ;      (= state newState)
   ;     (switchto state
    ;        (
     ;           (gGame handsOff:)
	  ;          (Prints {You hurl the beautiful blue box at the evil you.})
       ;         (gEgo put: INV_BOX) ; Remove from inventory
        ;        (theBox
         ;               posn: (gEgo x?) (gEgo y?) 30
          ;              show:
           ;             setMotion: JumpTo (evilEgo x?) (evilEgo y?) self
;                )
 ;           )
  ;          (
   ;             (evilEgo hide:)
	;            (Prints {Pop!})
     ;           (gGame handsOn:)
	;		    ; Add 5 points to the player's score:
	;		    (AddToScore 5)
     ;           ; Box hangs in the air for a couple of seconds for comedic effect
      ;          (= seconds 2)
       ;         (theBox posn: (theBox x?) (- (theBox y?) 30) 0)
        ;    )
         ;   (
          ;      (theBox setMotion: JumpTo (theBox x?) (+ (theBox y?) 30) self)
           ; )
    ;    )
   ; )
;)