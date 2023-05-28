TABLE DEFINITION

--------------------------------------------------------
| Flight Number |  Airline  |   Seats   |   Cabin Type |
--------------------------------------------------------
|    FL01       |    BA     |    5      |   Business   |
--------------------------------------------------------
|    FL01       |    BA     |    0      |   First      |
--------------------------------------------------------
|    FL01       |    BA     |    10     |   Economy    |
--------------------------------------------------------

(The table rows are representative values)

APIs:
- CRUD - User should be able to do all the CRUD operations
- PAGINATION
    - The API should return the number of records for a requested page.
    - First page should be returned if no pages are requested.
    - A default number of records should be returned if the number of records are not mentioned.
- SEARCH AMENITIES
    - User should be able to get the amenities for the provided flight number and cabin type from an EXTERNAL API based on seat availability.
    - A meaningful business error should be returned if no seats are available

EXTERNAL API:
- Create a local instance of JSON server

TECHNICAL STACK
- SpringBoot
- JPA
- PostgreSQL
- JSON Server
- JAVA 8