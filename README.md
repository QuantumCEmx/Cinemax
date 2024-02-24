


# Data Structure


## User Section
## User
    {
        "firstname" : "Siwakorn",
        "lastname" : "Jansangsri",
        "id" : "1909300007092",
        "password" : "fafasfa2312koia",
        "tel" :"0980746601",
        "birthdate" : "2002-03-21",

    }

## Member

    {   
        "user_id" : "1909300007092",
        "member_id" : 0,
        "member_type" : 0,
        "expire_date" : "2024-12-31 00:00:00",
        "create_date" : "2024-01-31 00:00:00",

    }

## MemberType
    {
        "member_type_id" : 0,   int     Number
        "member_name" : "",     String  [Silver , Gold , Platinum ]
        "discount" : "100"      int     [0-100]                      //discount percent

    }

## Theater Section

## Theater
    {
        "theater_id" : "T001",
        "theater_name" : "Cinema Paradise",

    }

## Movie
    {
        "movie_id" : "M001",
        "movie_name" : "Harry Potter",
        "duration" : 150;
        "genre" : ["Action","Fantacy"],
        "director" : "Chris Nolan",
    }

## Round 
    {
        "round_id" : "R001",
        "start_time" : "2024-06-01 19:30:00",
        "theater_id" : "T001", 
        "movie_id" : "M001",
    }

## Seat 
    {
        "theater_id" : "T001",
        "seat_normal" : 30,
        "seat_honeymoon" : 20,

    }