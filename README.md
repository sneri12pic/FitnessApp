## README.md — *Fitness Application*


# 🏃‍♂️ Fitness Tracker — Inspired by Google Fit and MacroFactor

This Fitness Application is inspired by the robust features and user-friendly design of **Google Fit**, offering an engaging and comfortable experience to help users track workouts and stay healthy.

---

## Key Features

*  **Calorie Counter:** Editable and updatable via the Home screen. With progress shown in arch.
  
<img width="350" height="1032" alt="image" src="https://github.com/user-attachments/assets/6576eea6-39a0-451a-a11c-7341b2e27ab1" />
<img width="350" height="1032" alt="image" src="https://github.com/user-attachments/assets/2b0f198e-df8d-4570-905c-732de72495ea" />

<img width="350" height="1032" alt="image" src="https://github.com/user-attachments/assets/490e9bb8-12b6-477e-8bbb-ac8ec4a999c2" />
<img width="350" height="1032" alt="image" src="https://github.com/user-attachments/assets/1342e0d3-2150-4706-bd8c-1fd94f180994" />

<img width="350" height="1032" alt="image" src="https://github.com/user-attachments/assets/30cc00bf-4a67-4e5c-ac4e-ffbea5c7cc19" />

<img width="350" height="1032" alt="image" src="https://github.com/user-attachments/assets/cfa54563-d960-43ca-bba5-e457a86dd308" />

*  **Workout Options:** Running/Jogging, Hiking, and Cycling.
<img width="350" height="1032" alt="image" src="https://github.com/user-attachments/assets/207c0ac0-c145-4436-9c59-58a0f5d47295" />


## Running Or Jogging 
 
<img width="350" height="1032" alt="image" src="https://github.com/user-attachments/assets/05fc2de5-c433-495a-a9cb-379d15c54c39" />

## GYM
<img width="350" height="1032" alt="image" src="https://github.com/user-attachments/assets/30bd9431-07b4-498a-a670-dd3dc1bf6603" />
<img width="350" height="1032" alt="image" src="https://github.com/user-attachments/assets/e1f46268-ee27-4d8f-950e-3fd470dda216" />
<img width="350" height="1032" alt="image" src="https://github.com/user-attachments/assets/cdb49c3d-8e28-4640-8473-36ebe8de400b" />

- ** Rest Times are in Progress**


**Cycling -- Coming Soon**

**Hiking**

<img width="350" height="1032" alt="image" src="https://github.com/user-attachments/assets/893be624-c7c0-4154-ab20-c845f5cc69a1" />




*  **Profile Tracking:** Track completed workouts and sort them by day.

<img width="350" height="1032" alt="image" src="https://github.com/user-attachments/assets/fd0b41d4-e145-476c-9e7d-c0045f81b67b" />


* **Settings**

<img width="350" height="1032" alt="image" src="https://github.com/user-attachments/assets/b7b368c0-9c9c-44c4-bdb7-1de7d2b23766" />



*  **Check Buttons:** Mark workouts as done — totals are updated in the Profile.
*  **Calm UI:** Creamy pink colors and soft gradients for eye comfort.
*  **Easy Navigation:** Bottom panel for quick activity switching.
*  **Location-based Workouts:** (Planned) Customize hiking and cycling options based on user’s location.
*  **Customizable Profile:** Users can edit details and change profile icons.
*  **Persistent User Profile:** Name, calorie limit, and profile image URI are stored in a local Room database.
*  **Daily Calories Tracker:** Stores calories goal, consumed calories, date, and associated user ID — ensuring daily progress is saved and restored even after app restarts.


---

## Application Structure

The app consists of **8 classes** and corresponding XML layouts:

1. **MainActivity** — Welcome screen. (`activity_main.xml`)
2. **HomeActivity** — Home screen with calorie counter. (`activity_home.xml`)
3. **ExerciseActivity** — Choose workouts. (`activity_exercise.xml`)
4. **ProfileActivity** — View and sort workout history and profile settings. (`activity_profile.xml`)
5. **SettingsActivity** — Manage personal details. (`activity_settings.xml`)
6. **RunningJoggingActivity** — Running/Jogging workouts.
7. **HikingActivity** — Hiking workouts.
8. **CyclingActivity** — Cycling workouts *(Design ready, implementation in progress).*

### Database Structure

The application uses **Room** for local persistence, with two main tables:

1. **UserProfile**  
   - `id` (Primary Key)  
   - `name` (String)  
   - `caloriesLimit` (Integer)  
   - `photoUri` (String — URI to profile image)  

2. **CaloriesTracker**  
   - `id` (Primary Key)  
   - `userId` (Foreign Key → UserProfile.id)  
   - `calories` (Integer)  
   - `consumedCalories` (Integer)  
   - `date` (String — in `yyyy-MM-dd` format)  

This design allows each user to have a persistent profile and maintain daily calorie logs.

---

## Workouts Breakdown

* **Running/Jogging:**
  Click an image to access Running/Jogging workouts. Each rectangle is a dropdown with workouts inside. Margins keep layout aligned.

* **Hiking:**
  Similar dropdowns with images like Arthur’s Seat, Pentlands, and Waterfall trails. Distance and time icons appear on open.

* **Cycling:**
  Uses the same structure as Hiking — functionality coming soon.

---

## Installation

### Prerequisites
- Android Studio 4.0 or higher
- Android SDK 21 or above
- Gradle 7.0+
### Steps
1. Clone the repo: `git clone https://github.com/sneri12pic/FitnessApp.git`
2. Open in Android Studio.
3. Sync Gradle and build the project.
4. Run on an emulator or physical device (API 21+).

---

## Improvements Planned

* More workout options.
* Location-based workout suggestions.
* Daily sort and filter in Profile.

---

## Screenshots

*(Add screenshots of Home, Exercise, Profile screens here if possible)*

---

## Image Sources

* Running: [Runner's World](https://www.runnersworld.com/uk/health/a39979994/things-ive-learned-about-pain-management-as-a-runner)
* Hiking:

  * [Peaks and Valleys](https://podcasters.spotify.com/pod/show/iamrjkalyani/episodes/Episode-02--Peaks-and-Valleys---Tamil-e11inhm)
  * [St Bernard’s Well](https://media.edinburgh.org/wp-content/uploads/2023/04/27155700/St-Bernards-Well.jpg)
  * [Pentland Hills](https://cdn.tribalogic.net/uploads/33/595809-the-pentland-hills-1.jpg)
  * [Waterfall](https://encrypted-tbn1.gstatic.com/licensed-image?q=tbn:ANd9GcRpbNI9TDAmvSeKjvl-RSQkCehgGxYw-Fzxs250PuNElNo2tzvXMwl2BwnfsvByrzjPZ6P1ZNCwxJ45Ap1YgDBV-ESxDORROYLv-DTkQA)
* Cycling: [Gravel Bike](https://www.zellamsee-kaprun.com/en/sport/summer/bike/gravel-bike)
* Icons: [Google Fonts](https://fonts.google.com/icons)

---

## Author

**Stepan Demianenko**
**stepan.demyanenko30@gmail.com**
**Student Edinbrugh Napier University**

---

## License

*This project is for academic purposes only.*
