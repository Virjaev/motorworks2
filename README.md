# Motorworks (Minecraft 1.21.1 - NeoForge 21.1.219)

## Items / Bloecke
- Motor: Rechtsklick mit Benzineimer = tanken (1 Eimer = 5 Min Laufzeit). Leere Hand = Tankanzeige.
- Benzin (Eimer + Fluid, wie Wasser): im Kreativ-Tab "Motorworks".
- Rad: dreht sich (animierte Textur), wenn ein laufender Motor direkt daneben steht, und erzeugt dann 40 FE/Tick.
- Kabel: verbindet sich automatisch mit Rad, Batterie, Lampe und anderen Kabeln.
- Batterie: Eingang = alle Seiten, Ausgang = die orangene Front (zeigt beim Platzieren zum Spieler). Rechtsklick zeigt Ladung.
- Lampe: leuchtet, solange sie Strom bekommt.

## Aufbau
Motor -> Rad (direkt daneben) -> Kabel -> Batterie (Seite/Hinten) ; Batterie-Front -> Kabel -> Lampe

## Starten
1. JDK 21 installieren.
2. Projekt in IntelliJ als Gradle-Projekt oeffnen (oder `gradlew runClient`, siehe unten).
3. Ohne gradle-wrapper: im Ordner einmal `gradle wrapper` ausfuehren (oder gradlew-Dateien aus dem NeoForge-MDK 1.21.1 hineinkopieren).
4. `gradlew runClient` zum Testen, `gradlew build` -> Jar in build/libs.

## Jar ohne Installation bauen (GitHub)
1. Auf github.com ein neues Repository erstellen und den kompletten Inhalt dieses Ordners hochladen (inkl. .github).
2. Reiter "Actions" -> "Build Mod" -> warten bis gruen.
3. Unten bei "Artifacts" motorworks-jar herunterladen, entpacken -> die .jar in den mods-Ordner.
