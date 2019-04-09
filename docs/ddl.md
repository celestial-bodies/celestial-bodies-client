```sql

CREATE TABLE IF NOT EXISTS `User` (
`user_id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,
 `name` TEXT, 
 `favorites` TEXT, 
 `city` TEXT, 
 `email` TEXT, 
 `timestamp` INTEGER
);

CREATE UNIQUE INDEX `index_User_user_id` ON `User` (
`user_id`
);

CREATE  INDEX `index_User_name` ON `User` (
`name`
);

CREATE  INDEX `index_User_favorites` ON `User` (
`favorites`
);

CREATE  INDEX `index_User_email` ON `User` (
`email`
);

CREATE TABLE IF NOT EXISTS `Star` (
`id` TEXT NOT NULL,
`time` INTEGER NOT NULL,
`catId` TEXT,
`de` TEXT,
`mag` TEXT,
`ra` TEXT,
PRIMARY KEY (`id`),
    );
    
    

```