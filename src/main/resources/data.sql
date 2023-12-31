INSERT INTO gameshop.genres (id, name) VALUES
                                           (1, 'ADVENTURE'),
                                           (2, 'FPS'),
                                           (3, 'RPG'),
                                           (4, 'SURVIVAL');

INSERT INTO gameshop.games (id, description, imageurl, is_deleted, price, quantity, release_year, title, videourl,genre_id)
VALUES
    (1, 'Cyberpunk 2077 is an open-world, action-adventure RPG set in the megalopolis of Night City, where you play as a cyberpunk mercenary wrapped-up in a do-or-die fight for survival. Explore the dark future',
     'https://i.redd.it/w44jvkcns8461.png',
     0, 31.00, 70 ,2020, 'Cyberpunk 2077', 'https://youtu.be/qIcTM8WXFjk', 2),
    (2, 'Fallout 4 is an open-world role-playing game developed by Bethesda Game Studios. In it, the player begins as a parent and spouse in the year 2077, but then must enter Vault 111 to survive the nuclear apocalypse.',
     'https://m.media-amazon.com/images/I/51JgsDBqOEL._AC_UF894,1000_QL80_.jpg',
     0, 31.00, 40 ,2020, 'Fallout 4', 'https://youtu.be/U3crIKmZTsA', 3),

    (3, 'Diablo 4 is the newest entry in the legendary dungeon-crawling action RPG. Players will fight against the horde of monsters controlled by Lilith, the daughter of Mephisto, one of the Great Evils, and called the "mother" of Sanctuary, the world in which the Diablo series takes place.',
     'https://image.api.playstation.com/vulcan/ap/rnd/202212/0522/Dzry5RAwU9HsJGXZ3PUSYgCa.jpg',
     0, 59.99, 300 ,2023, 'Diablo 4', 'https://youtu.be/XV4zVqb9vWc', 3),
    (4, 'Starfield is an action role-playing video game. The player can switch between a first-person and third-person perspective at any time. The game features an open world in the form of an area within the Milky Way galaxy, containing both fictional and non-fictional planetary systems.',
     'https://static.displate.com/857x1200/displate/2023-09-05/139121f7e02d7de65d346dcd0b0d3f8d_4ab9b9a94eb241dbb9a3f4537fc5cb69.jpg',
     0, 49.99, 190 ,2023, 'Starfield ', 'https://youtu.be/XV4zVqb9vWc', 3),
    (5, 'This narratively driven, single-player title picks up 5 years after the events of Star Wars Jedi: Fallen Order™ and follows Cal''s increasingly desperate fight as the galaxy descends further into darkness. Pushed to the edges of the galaxy by the Empire, Cal will find himself surrounded by threats new and familiar.',
     'https://i.redd.it/vn586yya37va1.jpg',
     0, 59.99, 130 ,2023, 'Star Wars Jedi: Fallen Order', 'https://youtu.be/XV4zVqb9vWc', 1),
    (6, 'A WORLD AFTER THE APOCALYPSE With the Harran virus spreading around the globe, people quickly found out that all hope for tomorrow is lost. By 2036, only a few settlements remain, and humanity is slowly dying, making way for the new species out there — a horde of relentless zombies',
     'https://m.media-amazon.com/images/M/MV5BZTg4NGUxZWMtNmU3OC00YTNmLTkwYWMtYjE3OWQ4ODY3ODBhXkEyXkFqcGdeQXVyMzY0MTE3NzU@._V1_.jpg',
     0, 39.99, 80 , 2022, 'Dying Light 2', 'https://youtu.be/XV4zVqb9vWc', 4),
    (7, 'Like its predecessor, the game takes place in a realistic and modern setting. The campaign follows multi-national special operations unit Task Force 141 and Mexican Special Forces unit Los Vaqueros as they attempt to track down terrorist leader, who is in possession of American-made ballistic missiles.',
     'https://imageio.forbes.com/specials-images/imageserve/628d337e791f9767c05ca2e7/1--2-/960x0.jpg?height=887&width=711&fit=bounds',
     0, 34.99, 100 , 2022, 'Call of Duty: Modern Warfare 2 ', 'https://youtu.be/XV4zVqb9vWc', 2),
(8, 'Kratos and Atreus must journey to each of the Nine Realms in search of answers as Asgardian forces prepare for a prophesied battle that will end the world. "Along the way they will explore stunning, mythical landscapes, and face fearsome enemies in the form of Norse gods and monsters.',
    'https://assets-prd.ignimgs.com/2022/07/25/9781506733494-1658716557072.jpg',
    0, 59.99, 170 ,2022, 'God of War Ragnarok', 'https://youtu.be/qIcTM8WXFjk', 1);
