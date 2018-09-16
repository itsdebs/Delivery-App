package com.debanjan.deliver

fun generateMockDeliveries(number:Int):MutableList<Delivery> =
        ArrayList<Delivery>(number).also {
        var lat = 22.0
        var lng = 88.0
        for (i in 0 until number){
            it.add(Delivery("Demo description. I love you",
                    "https://kids.nationalgeographic.com/content/dam/kids/photos/animals/Mammals/A-G/cheetah-watching.ngsversion.1396530528126.jpg"
                    , Location(lat,lng, "Demo address, demo building, demo lane- demo")))
            lat += .2
            lng += .2
        }
    }

