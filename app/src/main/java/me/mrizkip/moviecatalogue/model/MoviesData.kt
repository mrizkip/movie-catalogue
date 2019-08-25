package me.mrizkip.moviecatalogue.model

import me.mrizkip.moviecatalogue.R

object MoviesData {
    private val movieData = arrayOf(
        arrayOf(
            "Aquaman",
            "Once home to the most advanced civilization on Earth, Atlantis is now an underwater kingdom ruled by the power-hungry King Orm. With a vast army at his disposal, Orm plans to conquer the remaining oceanic people and then the surface world. Standing in his way is Arthur Curry, Orm\\'s half-human, half-Atlantean brother and true heir to the throne.",
            "December 7, 2018",
            "6.8",
            R.drawable.poster_aquaman,
            "2h 24m",
            "ACTION"
        ),
        arrayOf(
            "Avengers: Infinity War",
            "As the Avengers and their allies have continued to protect the world from threats too large for any one hero to handle, a new danger has emerged from the cosmic shadows: Thanos. A despot of intergalactic infamy, his goal is to collect all six Infinity Stones, artifacts of unimaginable power, and use them to inflict his twisted will on all of reality. Everything the Avengers have fought for has led up to this moment - the fate of Earth and existence itself has never been more uncertain.",
            "April 25, 2018",
            "8.3",
            R.drawable.poster_infinity_war,
            "2h 29m",
            "ACTION"
        ),
        arrayOf(
            "Bohemian Rhapsody",
            "Singer Freddie Mercury, guitarist Brian May, drummer Roger Taylor and bass guitarist John Deacon take the music world by storm when they form the rock \\'n\\' roll band Queen in 1970. Hit songs become instant classics. When Mercury\\'s increasingly wild lifestyle starts to spiral out of control, Queen soon faces its greatest challenge yet – finding a way to keep the band together amid the success and excess.",
            "October 24, 2018",
            "8.1",
            R.drawable.poster_bohemian,
            "2h 15m",
            "DRAMA"
        ),
        arrayOf(
            "Cold Pursuit",
            "The quiet family life of Nels Coxman, a snowplow driver, is upended after his son's murder. Nels begins a vengeful hunt for Viking, the drug lord he holds responsible for the killing, eliminating Viking's associates one by one. As Nels draws closer to Viking, his actions bring even more unexpected and violent consequences, as he proves that revenge is all in the execution.",
            "February 7, 2019",
            "5.4",
            R.drawable.poster_cold_persuit,
            "1h 59m",
            "ACTION"
        ),
        arrayOf(
            "Fantastic Beasts: The Crimes of Grindelwald",
            "Gellert Grindelwald has escaped imprisonment and has begun gathering followers to his cause—elevating wizards above all non-magical beings. The only one capable of putting a stop to him is the wizard he once called his closest friend, Albus Dumbledore. However, Dumbledore will need to seek help from the wizard who had thwarted Grindelwald once before, his former student Newt Scamander, who agrees to help, unaware of the dangers that lie ahead. Lines are drawn as love and loyalty are tested, even among the truest friends and family, in an increasingly divided wizarding world.",
            "November 14, 2018",
            "6.9",
            R.drawable.poster_crimes,
            "2h 14m",
            "ADVENTURE"
        ),
        arrayOf(
            "How to Train Your Dragon: The Hidden World",
            "As Hiccup fulfills his dream of creating a peaceful dragon utopia, Toothless’ discovery of an untamed, elusive mate draws the Night Fury away. When danger mounts at home and Hiccup’s reign as village chief is tested, both dragon and rider must make impossible decisions to save their kind.",
            "January 3, 2019",
            "7.7",
            R.drawable.poster_how_to_train,
            "1h 44m",
            "ANIMATION"
        ),
        arrayOf(
            "Overlord",
            "France, June 1944. On the eve of D-Day, some American paratroopers fall behind enemy lines after their aircraft crashes while on a mission to destroy a radio tower in a small village near the beaches of Normandy. After reaching their target, the surviving paratroopers realise that, in addition to fighting the Nazi troops that patrol the village, they also must fight against something else.",
            "November 1, 2018",
            "6.6",
            R.drawable.poster_overlord,
            "1h 50m",
            "HORROR"
            ),
        arrayOf(
            "Ralph Breaks the Internet",
            "Video game bad guy Ralph and fellow misfit Vanellope von Schweetz must risk it all by traveling to the World Wide Web in search of a replacement part to save Vanellope's video game, \"Sugar Rush.\" In way over their heads, Ralph and Vanellope rely on the citizens of the internet -- the netizens -- to help navigate their way, including an entrepreneur named Yesss, who is the head algorithm and the heart and soul of trend-making site BuzzzTube.",
            "November 20, 2018",
            "7.2",
            R.drawable.poster_ralph,
            "1h 52m",
            "FAMILY"
            ),
        arrayOf(
            "Robin Hood",
            "A war-hardened Crusader and his Moorish commander mount an audacious revolt against the corrupt English crown.",
            "November 21, 2018",
            "5.8",
            R.drawable.poster_robin_hood,
            "1h 56m",
            "ACTION"
        ),
        arrayOf(
            "Spider-Man: Into the Spider-Verse",
            "Miles Morales is juggling his life between being a high school student and being a spider-man. When Wilson \"Kingpin\" Fisk uses a super collider, others from across the Spider-Verse are transported to this dimension.",
            "December 6, 2018",
            "8.4",
            R.drawable.poster_spiderman,
            "1h 57m",
            "ACTION"
            )
    )

    val listData: ArrayList<Movie>
        get() {
            val list = arrayListOf<Movie>()
            for (aMovie in movieData) {
                val movie = Movie()
                movie.title = aMovie[0].toString()
                movie.description = aMovie[1].toString()
                movie.releaseDate = aMovie[2].toString()
                movie.userRating = aMovie[3].toString()
                movie.poster = aMovie[4] as Int
                movie.runtime = aMovie[5].toString()
                movie.genre = aMovie[6].toString()

                list.add(movie)
            }

            return list
        }
}