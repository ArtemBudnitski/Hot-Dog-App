# Not Hot Dog App

| Object Mode | Hotdog mode True | Hotdog mode false |
| ---- | ----| ---- |
|![Screenshot_2024-04-10-13-45-20-43_2b624583f8ef78b54f21d26192f53cf0](https://github.com/ArtemBudnitski/Hot-Dog-App/assets/126951785/833b4c9c-c04a-4b44-931d-2f3526619462)|![Screenshot_2024-04-10-14-04-27-04_2b624583f8ef78b54f21d26192f53cf0](https://github.com/ArtemBudnitski/Hot-Dog-App/assets/126951785/262aa347-b425-437e-bb42-a2e4edc97f49)|![Screenshot_2024-04-10-14-04-50-87_2b624583f8ef78b54f21d26192f53cf0](https://github.com/ArtemBudnitski/Hot-Dog-App/assets/126951785/8d69050b-0211-4299-a067-f12e5ca96eca)|


## PL

### Opis
"Not a Hot Dog" to aplikacja mobilna inspirowana popularnym serialem "Silicon Valley". Aplikacja wykorzystuje technologię rozpoznawania obrazów do określania, czy obiekt przedstawiony na zdjęciu jest hot dogiem czy nie.

### Fragment z serialu "Silicon Valley" z aplikacją:
https://www.youtube.com/watch?v=vIci3C4JkL0

### Zastosowane technologie
- **Języki programowania:** Kotlin
- **Biblioteki:** 
  - Android SDK
  - Hilt
  - CameraX
  - Compose
  - MLKit

### Funkcje
- **Rozpoznawanie obiektów:** Aplikacja umożliwia skanowanie obiektów przy użyciu kamery smartfona.
- **Hot Dog Mode:** Włącz tryb "Hot Dog", aby sprawdzić, czy skanowany obiekt jest hot dogiem. Aplikacja wykorzystuje technologię ML Kit do analizy obrazu.
- **Inne obiekty:** Nie tylko hot dogi! Aplikacja umożliwia rozpoznawanie różnych obiektów, co czyni ją wszechstronnym narzędziem do zabawy i eksperymentowania.

### Struktura projektu
- `MainActivity`: Główna aktywność, odpowiedzialna za zarządzanie uprawnieniami kamery i inicjalizację interfejsu użytkownika.
- `MainScreen`: Komponent Compose reprezentujący interfejs główny z podglądem kamery i funkcjonalnością rozpoznawania obrazów.
- `CameraPermissionRequestScreen`: Ekran informujący użytkownika o konieczności udzielenia uprawnień do kamery.
- `MainScreenViewModel`: Model widoku odpowiadający za logikę interakcji w głównym ekranie.
- `LabelMapper`: Klasa mapująca obiekty ImageLabel na obiekty Label.
- `LabelUiMapper`: Klasa mapująca obiekty Label na obiekty UiLabel.

## ENG

### Description
"Not a Hot Dog" is a mobile application inspired by the popular TV series "Silicon Valley". The app uses image recognition technology to determine whether the object pictured is a hot dog or not.

### Excerpt from the TV series "Silicon Valley" with the app:
https://www.youtube.com/watch?v=vIci3C4JkL0

### Technologies used
- **Programming languages:** Kotlin
- **Libraries:**
  - Android SDK
  - Hilt
  - CameraX
  - Compose
  - MLKit

### Features.
- **Object Recognition:** The application allows you to scan objects using your smartphone's camera.
- **Hot Dog Mode:** Enable "Hot Dog" mode to see if the scanned object is a hot dog. The app uses ML Kit technology to analyze the image.
- **Other Objects:** Not just hot dogs! The app allows you to recognize different objects, making it a versatile tool for fun and experimentation.

### Project structure
- `MainActivity`: The main activity, responsible for managing camera permissions and initializing the user interface.
- `MainScreen`: Compose component representing the main interface with camera preview and image recognition functionality.
- `CameraPermissionRequestScreen`: A screen informing the user to grant permissions to the camera.
- `MainScreenViewModel`: The view model responsible for the interaction logic in the main screen.
- `LabelMapper`: A class that maps ImageLabel objects to Label objects.
- `LabelUiMapper`: A class that maps Label objects to objects of the
