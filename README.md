# Space Shooter

A 2D arcade game built in Java with Swing. Pilot a spaceship, shoot falling asteroids, and try to beat your high score.

## Features
- Object-oriented design with separate classes for the ship, asteroid, bullets, stars and moon
- Real-time update and render loop
- Collision detection, lives, scoring and restart
- Parallax scrolling starfield
- Optional online leaderboard over a REST API (the game works offline if the server is down)

## Controls
| Key | Action |
|---|---|
| Any key | Start the game |
| Arrow keys | Move the ship |
| Space | Shoot |
| R | Restart after game over |

## Requirements
- Java 11 or newer (the leaderboard client uses `java.net.http.HttpClient`)

## Run it
1. Clone the repo.
2. Open it in IntelliJ IDEA.
3. Run `Main.java`.

## Leaderboard (optional)
The game talks to a score service with these endpoints:
- `POST /scores` submits a score (JSON body with `name` and `score`)
- `GET /scores/top` returns the top 10

Settings:
- `SCORES_API_KEY` (environment variable): API key sent in the `X-API-Key` header
- `-Dscores.url=...` (JVM option): server URL, default `http://localhost:8080`

If the server isn't running, the game shows "Leaderboard offline" and plays normally.

## What I learned
- Loading images with `ImageIO` and drawing them in a Swing window
- Consuming a REST API from Java using `HttpClient`, including sending JSON and handling timeouts
- Handling failure gracefully: the game keeps working if the leaderboard server is down