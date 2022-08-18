DO
$$
BEGIN
    IF NOT EXISTS (SELECT 1 FROM pg_type WHERE typname = 'booking_status') THEN
       CREATE TYPE booking_status AS ENUM('VALID','USED','EXPIRED','CANCELLED');
    ENDIF;
    IF NOT EXISTS (SELECT 1 FROM pg_type WHERE typname = 'seat_type') THEN
       CREATE TYPE seat_type AS ENUM('FIRST_CLASS','COACH');
    ENDIF;
    IF NOT EXISTS (SELECT 1 FROM pg_type WHERE typname = 'user_role') THEN
        CREATE TYPE user_role AS ENUM('PASSENGER','OFFICER','ADMIN');
    ENDIF;
    IF NOT EXISTS (SELECT 1 FROM pg_type WHERE typname = 'voyage_status') THEN
        CREATE TYPE voyage_status AS ENUM('PENDING','IN_TRANSIT','COMPLETED','CANCELLED');
    ENDIF;

   END
    $$;