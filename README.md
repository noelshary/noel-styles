# Neoloni Styles 2.0.0

Clientseitige Fabric-Mod für Minecraft 1.21.11 / Java 21. Taste **K** öffnet das Menü. Pro Kategorie kann ein eingebautes Resourcepack aktiviert oder auf **Standard** zurückgesetzt werden.

## Enthalten

- Die ursprünglichen 13 Styles (Kristalle, Feuer, Glowstone, Stein)
- 135 zusätzliche, lokal eingebettete Varianten in 18 Kategorien: Schwerter, Äxte, Spitzhacken, Schilde, Totems, Enderperlen, Respawn-Anker, End-Kristalle, Diamant-/Netherite-Rüstungsakzente, Himmel, Obsidian, Wasser, Lava, Crosshair, Hotbar, Herzen und Inventar
- Kategorienavigation im Menü; Taste K bleibt der Öffner
- GitHub Actions baut die Mod mit Java 21 und lädt das JAR als Artifact hoch

## Wichtige Funktionsgrenzen dieser Entwicklungsfassung

- Die Himmel-Varianten färben Sonne/Mond; sie sind **keine vollständigen 3D-Skyboxen**. Sterne und Wolken sind noch nicht als vollständiges Himmelssystem implementiert.
- Die Rüstungsvarianten ändern in dieser Fassung die Brustplatten-Itemdarstellung, nicht die vollständigen getragenen Rüstungs-Layer.
- Wasser/Lava sind einfache Textur-Overrides und keine aufwendigen animierten Flüssigkeits-Shader.
- Vorschau-Bilder pro Style und ein eigener globaler Preview-Modus sind noch nicht vorhanden.

## Datenschutz / Netzwerk

Der Mod-Quellcode enthält keine HTTP-Clients, Downloader, Telemetrie oder Update-Prüfung. Die Styles sind in der JAR eingebettete Resourcepacks. Die GitHub-Actions-Datei kommuniziert nur mit GitHub zum Bauen und Ablegen des Build-Artifacts; das ist kein Laufzeitverhalten der Minecraft-Mod.

## Bauen

Mit Gradle 9.2.1 und Java 21:

```bash
gradle --no-daemon clean build
```

Die JAR liegt anschließend unter `build/libs/`. Alternativ im GitHub-Repository **Actions → Build Neoloni Styles 2.0 → Run workflow** starten und nach erfolgreichem Lauf das Artifact herunterladen.
