package likco.likfit.models

data class User(val id: String, val login: String, var profile: Profile? = null)
