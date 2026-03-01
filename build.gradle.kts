plugins {
    // this is necessary to avoid the plugins to be loaded multiple times
    // in each subproject's classloader
    alias(libs.plugins.androidApplication) apply false
    alias(libs.plugins.androidLibrary) apply false
    alias(libs.plugins.composeMultiplatform) apply false
    alias(libs.plugins.composeCompiler) apply false
    alias(libs.plugins.kotlinMultiplatform) apply false
    alias(libs.plugins.vkidManifestPlaceholders) apply true
}

// Добавление значений в Manifest Placeholders.
vkidManifestPlaceholders {
    val localProperties = java.util.Properties()
    localProperties.load(rootProject.file("keystore.properties").inputStream())
    // Добавьте плейсхолдеры сокращенным способом. Например, vkidRedirectHost будет "vk.ru", а vkidRedirectScheme будет "vk$clientId".
    init(
        clientId = localProperties["VK_CLIENT_ID"] as String,
        clientSecret = localProperties["VK_CLIENT_SECRET"] as String,
    )

    // Или укажите значения явно через properties, если не хотите использовать плейсхолдеры.
    vkidRedirectHost = "vk.ru" // Обычно vk.ru.
    vkidRedirectScheme = "vk$vkidClientId" // Строго в формате vk{ID приложения}.
    vkidClientId = localProperties["VK_CLIENT_ID"] as String
    vkidClientSecret = localProperties["VK_CLIENT_SECRET"] as String
}