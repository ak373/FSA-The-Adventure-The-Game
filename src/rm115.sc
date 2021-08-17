;;; Sierra Script 1.0 - (do not remove this comment)
(script# 115)
(include sci.sh)
(include game.sh)
(include 115.shm)
(include 115.shp)
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
	rm115 0
)

(instance rm115 of Room
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
		(AddPolygonsToRoom @P_Default115)
		(super init:)
		(self setScript: RoomScript)
		(switch gPreviousRoomNumber
		    (112
		        (SetUpEgo -1 2) ; 3 is the loop of the ego where he's facing up.
		        (gEgo posn: 40 175) ; this is the position of the ego
		    )
		)
		(gEgo init:)
		
		(magicShelves
			setOnMeCheck: omcPOLYGON (CreateNewPolygon @P_MagicShelves)
		    init:
		)
		(miniMagicDoor
			approachVerbs: V_DO
			setOnMeCheck: omcPOLYGON (CreateNewPolygon @P_MiniMagicDoor)
		    init:
		)
		(magicWindow
			setOnMeCheck: omcPOLYGON (CreateNewPolygon @P_MagicWindow)
		    init:
		)
		(penny
			setOnMeCheck: omcPOLYGON (CreateNewPolygon @P_Penny)
		    init:
		)
		(crystalBall
			setOnMeCheck: omcPOLYGON (CreateNewPolygon @P_CrystalBall)
		    init:
		)
		(joe
			approachVerbs: V_TALK
			setOnMeCheck: omcPOLYGON (CreateNewPolygon @P_Joe)
		    init:
		)
	)
)

(instance RoomScript of Script
	(properties)
	
	(method (doit)
	    ; If the ego is on green
;	    (if (& (gEgo onControl:) ctlGREEN)
;	        (gRoom newRoom: 112)
;	    )
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
            	(if (not (Btest F_115_Started))
    				(Bset F_115_Started)
					(Prints {You don't know where you are, but you're certainly... somewhere. Odd, arcane looking items clutter the walls and floor, and a grand view of the mountains can be seen through the back.})
        			(Prints {Wait. Weren't you underground?})
        			(Prints {The man at the far end of the room looks up with a start.})
        			(Prints {"Hello! You are the new one, yes? So glad to have you!"})
        			(Prints {"Don't get caught up in that 'science' nonsense. There is no 'reasonable explanation' for everything - there is only magic!"})
        			(Prints {"I am the Grand Wizard Joe!"})
        			(Prints {"And this is my familiar, Penny."})
        			(Prints {"Arf! Arf!~"})
        			(Prints {"Not sure how I ended up with a barking gargoyle. Oh well - you don't choose your loved ones."})
        			(Prints {"Though except in this case. I did literally choose Penny."})
        			(Prints {"Regardless, please come to me for all your magic-related needs."})
				else
					(Prints {Joe smiles and Penny flutters its wings in greeting.})
				)
			)
		)
	)
)

;;;
;;; ROOM INTERACTIBLES
;;;

(instance joe of Feature
	(properties
		x 256
		y 78
		approachX 198
		approachY 139
		noun N_JOE
	)
	(method (doVerb theVerb)
        (switch theVerb
            (V_TALK
            	(if (Btest F_BookDone)
	                (Prints {"This tome is incredible! Whoever wrote this must have truly been a student of the magical arts!"})
	                (Prints {"Arf arf arf!"})
				else
					(Prints {"Welcome! Hope you're having a mystical time so far. Let me know how I can help."})
	                (Prints {"Arf!"})
				)
            )
            (V_BOOK
            	(Bset F_BookQuestGiven)
				(gEgo put: INV_BOOK)
				(gEgo get: INV_OTHERBOOK)
				(Prints {"Ugh! Harry Potter? That's the best you could do? It is a mockery of all wizardry! An insult!"})
				(Prints {"Do you see me waving a wand around like I'm in some party act?! Do I speak pretend Latin?! NONSENSE!"})
				(Prints {"Take that away! No book bearing the title 'Harry Potter' will EVER disgrace my shelves!"})
				(Prints {Penny whimpers.})
				(if (gEgo has: INV_KNIFE)
            		(gEgo put: INV_OTHERBOOK)
            		(gEgo put: INV_KNIFE)
					(gEgo get: INV_TORNBOOK)
            		(Prints {With a sly smirk, you use the knife to remove the cover of the Harry Potter book set.})
				)     
			)
            (V_OTHERBOOK
            	(if (gEgo has: INV_KNIFE)
            		(gEgo put: INV_OTHERBOOK)
            		(gEgo put: INV_KNIFE)
					(gEgo get: INV_TORNBOOK)
            		(Prints {With a sly smirk, you use the knife to remove the cover of the Harry Potter book set.})
            	else
            		(Prints {"Harry Potter! Poppycock! The mere sight of the cover disgusts me!"})
					(Prints {Penny growls.})
				)            
			)
            (V_TORNBOOK
            	(Bset F_BookDone)
				(gEgo put: INV_TORNBOOK)
				(gEgo get: INV_BOX)
				(Prints {"Hmm. What's this?"})
				(Prints {"A book so traversed that its cover has been unhinged from the spine? A spectacular book it must be indeed!"})
				(Prints {Joe scans through the pages.})
				(Prints {"Yes, wonderful! This will be perfect to grace my shelves!"})
				(Prints {"It's dangerous to go alone. Take this - I insist!"})
				(Prints {Joe hands you a small box. It is extremely light-weight and fits in the palm of your hand.})
				(Prints {"This box - light as a feather - will grow tremendous in size once thrown! A pretty handy thing to carry around, don't you think?"})
				(Prints {"You may be wondering why I have something like this ready to go. All I can say is that if you studied the art of SciScripting as I have then you'd understand."})
				(Prints {"But don't worry about that! Thank you! And good luck!"})
				(Prints {Penny flutters its wings in celebration.})
			)
            (else
                (super doVerb: theVerb &rest)
            )
        )
    )
)
(instance penny of Feature
	(properties
		x 200
		y 43
		noun N_PENNY
	)
)
(instance miniMagicDoor of Feature
	(properties
		x 258
		y 141
		approachX 248
		approachY 154
		noun N_MINIMAGICDOOR
	)
	(method (doVerb theVerb)
        (switch theVerb
            (V_DO
            (Prints {"Come again!"})
            (gRoom newRoom: 112)
            )
            (else
                (super doVerb: theVerb &rest)
            )
        )
    )
)
(instance magicShelves of Feature
	(properties
		x 97
		y 108
		noun N_MAGICSHELVES
	)
	(method (doVerb theVerb)
        (switch theVerb
            (V_LOOK
            	(if (Btest F_BookDone)
	                (Prints {Impressive collection.})
				else
					(Prints {"Surveying my collection, I see! I've acquired various books and trinkets in my time, but it feels yet incomplete."})
					(Prints {"Another book - I think - is what I need. Something that'll fit right... there. It will bring balance to the four sections."})
	                (Prints {Penny whines.})
				)
            )
            (else
                (super doVerb: theVerb &rest)
            )
        )
    )
)
(instance magicWindow of Feature
	(properties
		x 281
		y 68
		noun N_MAGICWINDOW
	)
)
(instance crystalBall of Feature
	(properties
		x 305
		y 76
		noun N_CRYSTALBALL
	)
)