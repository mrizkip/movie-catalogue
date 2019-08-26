package me.mrizkip.moviecatalogue.model

import me.mrizkip.moviecatalogue.R

object TvShowsData {
    private val tvShowData = arrayOf(
        arrayOf(
            "Arrow",
            "Spoiled billionaire playboy Oliver Queen is missing and presumed dead when his yacht is lost at sea. He returns five years later a changed man, determined to clean up the city as a hooded vigilante armed with a bow.",
            "October 10, 2012",
            "5.8",
            R.drawable.poster_arrow,
            "8",
            "CRIME"
        ),
        arrayOf(
            "Dragon Ball",
            "Long ago in the mountains, a fighting master known as Gohan discovered a strange boy whom he named Goku. Gohan raised him and trained Goku in martial arts until he died. The young and very strong boy was on his own, but easily managed. Then one day, Goku met a teenage girl named Bulma, whose search for the dragon balls brought her to Goku's home. Together, they set off to find all seven dragon balls in an adventure.",
            "February 26, 1986",
            "7.0",
            R.drawable.poster_dragon_ball,
            "2",
            "ANIMATION"
        ),
        arrayOf(
            "Fairy Tail",
            "Lucy is a 17-year-old girl, who wants to be a full-fledged mage. One day when visiting Harujion Town, she meets Natsu, a young man who gets sick easily by any type of transportation. But Natsu isn't just any ordinary kid, he's a member of one of the world's most infamous mage guilds: Fairy Tail.",
            "October 12, 2009",
            "6.4",
            R.drawable.poster_fairytail,
            "9",
            "ANIMATION"
        ),
        arrayOf(
            "Family Guy",
            "Sick, twisted, politically incorrect and Freakin' Sweet animated series featuring the adventures of the dysfunctional Griffin family. Bumbling Peter and long-suffering Lois have three kids. Stewie (a brilliant but sadistic baby bent on killing his mother and taking over the world), Meg (the oldest, and is the most unpopular girl in town) and Chris (the middle kid, he's not very bright but has a passion for movies). The final member of the family is Brian - a talking dog and much more than a pet, he keeps Stewie in check whilst sipping Martinis and sorting through his own life issues.",
            "January 31, 1999",
            "6.5",
            R.drawable.poster_family_guy,
            "18",
            "COMEDY"
        ),
        arrayOf(
            "The Flash",
            "After a particle accelerator causes a freak storm, CSI Investigator Barry Allen is struck by lightning and falls into a coma. Months later he awakens with the power of super speed, granting him the ability to move through Central City like an unseen guardian angel. Though initially excited by his newfound powers, Barry is shocked to discover he is not the only \"meta-human\" who was created in the wake of the accelerator explosion -- and not everyone is using their new powers for good. Barry partners with S.T.A.R. Labs and dedicates his life to protect the innocent. For now, only a few close friends and associates know that Barry is literally the fastest man alive, but it won't be long before the world learns what Barry Allen has become...The Flash.",
            "December",
            "6.6",
            R.drawable.poster_flash,
            "2",
            "SCI-FI & FANTASY"
        ),
        arrayOf(
            "Gotham",
            "Before there was Batman, there was GOTHAM.\n" +
                    "Everyone knows the name Commissioner Gordon. He is one of the crime world's greatest foes, a man whose reputation is synonymous with law and order. But what is known of Gordon's story and his rise from rookie detective to Police Commissioner? What did it take to navigate the multiple layers of corruption that secretly ruled Gotham City, the spawning ground of the world's most iconic villains? And what circumstances created them – the larger-than-life personas who would become Catwoman, The Penguin, The Riddler, Two-Face and The Joker?",
            "September 22, 2014",
            "6.9",
            R.drawable.poster_gotham,
            "6",
            "CRIME"
        ),
        arrayOf(
            "Marvel's Iron Fist",
            "Danny Rand resurfaces 15 years after being presumed dead. Now, with the power of the Iron Fist, he seeks to reclaim his past and fulfill his destiny.",
            "March 17, 2017",
            "6.1",
            R.drawable.poster_iron_fist,
            "2",
            "ACTION & ADVENTURE"
        ),
        arrayOf(
            "Naruto Shippuuden",
            "Naruto Shippuuden is the continuation of the original animated TV series Naruto.The story revolves around an older and slightly more matured Uzumaki Naruto and his quest to save his friend Uchiha Sasuke from the grips of the snake-like Shinobi, Orochimaru. After 2 and a half years Naruto finally returns to his village of Konoha, and sets about putting his ambitions to work, though it will not be easy, as He has amassed a few (more dangerous) enemies, in the likes of the shinobi organization; Akatsuki.",
            "February 15, 2007",
            "7.6",
            R.drawable.poster_naruto_shipudden,
            "21",
            "ANIMATION"
        ),
        arrayOf(
            "The Simpsons",
            "Set in Springfield, the average American town, the show focuses on the antics and everyday adventures of the Simpson family; Homer, Marge, Bart, Lisa and Maggie, as well as a virtual cast of thousands. Since the beginning, the series has been a pop culture icon, attracting hundreds of celebrities to guest star. The show has also made name for itself in its fearless satirical take on politics, media and American life in general.",
            "December 17, 1989",
            "7.1",
            R.drawable.poster_the_simpson,
            "31",
            "ANIMATION"
        ),
        arrayOf(
            "The Umbrella Academy",
            "A dysfunctional family of superheroes comes together to solve the mystery of their father's death, the threat of the apocalypse and more.",
            "February 15, 2019",
            "7.6",
            R.drawable.poster_the_umbrella,
            "1",
            "ACTION & ADVENTURE"
        )
    )

    val listData: ArrayList<TvShow>
        get() {
            val list = arrayListOf<TvShow>()
            for (aTvShow in tvShowData) {
                val tvShow = TvShow()
                tvShow.title = aTvShow[0].toString()
                tvShow.description = aTvShow[1].toString()
                tvShow.releaseDate = aTvShow[2].toString()
                tvShow.userRating = aTvShow[3].toString()
                tvShow.poster = aTvShow[4] as Int
                tvShow.seasons = aTvShow[5].toString()
                tvShow.genre = aTvShow[6].toString()

                list.add(tvShow)
            }

            return list
        }
}