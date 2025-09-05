# Demo Marvel API 🦸‍♂️

Una aplicación Android nativa desarrollada en Kotlin que demuestra el uso de varias tecnologías modernas para consumir la API de Marvel Comics.

## 📱 Descripción

Esta aplicación es una demostración práctica de cómo integrar y consumir la API oficial de Marvel Comics en una aplicación Android nativa, implementando las mejores prácticas de desarrollo móvil y arquitectura limpia.

## 🚀 Tecnologías Utilizadas

- **Kotlin** — Lenguaje de programación principal
- **Android SDK** — Plataforma de desarrollo
- **Marvel API** — API oficial de Marvel Comics
- **Gradle** — Sistema de construcción

## ✨ Características

- 📚 Exploración de personajes de Marvel
- 🔍 Búsqueda de héroes y villanos
- 📖 Información detallada de cada personaje
- 🎨 Interfaz de usuario moderna y atractiva
- 🌐 Consumo de API REST
- 📱 Diseño responsivo para diferentes tamaños de pantalla

## 🛠️ Instalación

### Prerrequisitos

- Android Studio Arctic Fox o superior
- JDK 11 o superior
- SDK de Android (API nivel 21 o superior)
- Clave de API de Marvel Developer Portal

### Pasos de instalación

1. Clona el repositorio
   ```bash
   git clone https://github.com/pirataRam/Demo-Marvel-API.git
   cd Demo-Marvel-API
   ```
2. Configura tu API Key de Marvel
   - Regístrate en [Marvel Developer Portal](https://developer.marvel.com/)
   - Obtén tu clave pública y privada
   - Agrega las claves en tu archivo `local.properties`:
   ```properties
   MARVEL_PUBLIC_KEY="tu_clave_publica_aqui"
   MARVEL_PRIVATE_KEY="tu_clave_privada_aqui"
   ```
3. Abre el proyecto en Android Studio
   - Open an existing project → selecciona la carpeta del repo
4. Sincroniza el proyecto y resuelve dependencias
5. Ejecuta la aplicación (dispositivo físico o emulador)

## 📁 Estructura del Proyecto

```
Demo-Marvel-API/
├── app/
│   ├── src/
│   │   ├── main/
│   │   │   ├── java/
│   │   │   ├── res/
│   │   │   └── AndroidManifest.xml
│   │   └── test/
│   └── build.gradle.kts
├── gradle/
├── build.gradle.kts
├── settings.gradle.kts
└── README.md
```

## 🔧 Configuración de la API de Marvel

Para utilizar la API de Marvel, necesitas:

1. Autenticación: la API requiere una clave pública, una clave privada y un timestamp.
2. Hash MD5: genera un hash MD5 usando `timestamp + privateKey + publicKey`.
3. Parámetros requeridos:
   - `ts` (timestamp)
   - `apikey` (clave pública)
   - `hash` (hash MD5 generado)

> Consejo: evita exponer la clave privada en el cliente. Usa ofuscación y/o un backend proxy si es para producción.

## 🎯 Funcionalidades Principales

### Listado de Personajes
- Visualización de personajes de Marvel en lista/grid
- Carga paginada para optimizar el rendimiento
- Imágenes de alta calidad de cada personaje

### Búsqueda
- Búsqueda en tiempo real de personajes
- Filtros por nombre
- Resultados instantáneos

### Detalles del Personaje
- Información completa del personaje seleccionado
- Biografía y descripción
- Comics, series y eventos relacionados

## 🧪 Testing

Ejecuta las pruebas unitarias:
```bash
./gradlew test
```

Pruebas instrumentadas:
```bash
./gradlew connectedAndroidTest
```

## 📄 Licencia

Este proyecto está bajo la Licencia Apache 2.0. Consulta el archivo [LICENSE](LICENSE) para más detalles.

## 🤝 Contribuciones

Las contribuciones son bienvenidas. Para contribuir:

1. Haz fork del proyecto
2. Crea una rama (`git checkout -b feature/AmazingFeature`)
3. Commit a tus cambios (`git commit -m 'feat: agrega AmazingFeature'`)
4. Push a la rama (`git push origin feature/AmazingFeature`)
5. Abre un Pull Request

## 👨‍💻 Autor

**pirataRam** — Desarrollador Android

- GitHub: [@pirataRam](https://github.com/pirataRam)

## 🙏 Agradecimientos

- [Marvel Comics](https://www.marvel.com/) por proporcionar la API
- [Marvel Developer Portal](https://developer.marvel.com/) por la documentación
- La comunidad de desarrolladores Android por las mejores prácticas

---

⭐ Si este proyecto te fue útil, ¡no olvides darle una estrella!

**Data provided by Marvel. © 2025 MARVEL**
