## README.md — *Fitness Application*


# 🏃‍♂️ Fitness Tracker — Inspired by Google Fit and MacroFactor

This Fitness Application is inspired by the robust features and user-friendly design of **Google Fit**, offering an engaging and comfortable experience to help users track workouts and stay healthy.

---

## Key Features

*  **Calorie Counter:** Editable and updatable via the Home screen. With progress shown in arch.
<img width="350" height="1032" alt="image" src="https://github.com/user-attachments/assets/0fe6512e-3dde-4524-8e55-66c5d3a22c9a" />
<img width="350" height="1032" alt="image" src="https://github.com/user-attachments/assets/83511ddc-92f6-430d-98ed-cf8e0c68bd16" />
<img width="350" height="1032" alt="image" src="https://github.com/user-attachments/assets/7d5d796e-d204-4c04-980b-b9ef1df60b65" />





*  **Workout Options:** Running/Jogging, Hiking, and Cycling.
*  **Profile Tracking:** Track completed workouts and sort them by day.
*  **Check Buttons:** Mark workouts as done — totals are updated in the Profile.
*  **Calm UI:** Creamy pink colors and soft gradients for eye comfort.
*  **Easy Navigation:** Bottom panel for quick activity switching.
*  **Location-based Workouts:** (Planned) Customize hiking and cycling options based on user’s location.
*  **Customizable Profile:** Users can edit details and change profile icons.

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

1. **Clone the repo:**

   ```bash
   https://github.com/sneri12pic/FitnessApp.git
   ```

2. **Open in Android Studio** (or your preferred IDE).

3. **Build & Run.**

---

## Improvements Planned

* Improved layout margins for various devices.
* More workout options.
* Location-based workout suggestions.
* Daily sort and filter in Profile.
* Profile icon customization.
* Working Calories Tracker

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
