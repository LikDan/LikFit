package likco.likfit.i18n

enum class Strings {
    ABINARY,
    AGENDER,
    AMBIGENDER,
    ANDROGYNE,
    ANDROGYNOS,
    ANDROGYNOUS,
    APORAGENDER,
    AUTIGENDER,
    BAKLA,
    BIGENDER,
    BINARY,
    BISSU,
    BUTCH,
    CALABAI,
    CALALAI,
    CIS,
    CISGENDER,
    CIS_FEMALE,
    CIS_MALE,
    CIS_MAN,
    CIS_WOMAN,
    DEMI_BOY,
    DEMIFLUX,
    DEMIGENDER,
    DEMI_GIRL,
    DEMI_GUY,
    DEMI_MAN,
    DEMI_WOMAN,
    DUAL_GENDER,
    ENDOSEX,
    EUNUCH,
    FAAFAFINE,
    FEMALE,
    FEMALE_TO_MALE,
    FEMME,
    FTM,
    GENDER_BENDER,
    GENDER_CREATIVE,
    GENDER_DIVERSE,
    GENDER_GIFTED,
    GENDERFLUID,
    GENDERFLUX,
    GENDERFUCK,
    GENDERLESS,
    GENDERVAGUE,
    GENDER_NONCONFORMING,
    GENDERQUEER,
    GENDER_QUESTIONING,
    GENDER_VARIANT,
    GRAYGENDER,
    HIJRA,
    INTERGENDER,
    INTERSEX,
    IPSOGENDER,
    KATHOEY,
    MALE,
    MALE_TO_FEMALE,
    MAN,
    MAN_OF_TRANS_EXPERIENCE,
    MAVERIQUE,
    MTF,
    MULTIGENDER,
    MUXE,
    NEITHER,
    NEUROGENDER,
    NEUTROIS,
    NON_BINARY,
    NON_BINARY_TRANSGENDER,
    OMNIGENDER,
    OTHER,
    PANGENDER,
    POLYGENDER,
    PERSON_OF_TRANSGENDERED_EXPERIENCE,
    QUEER,
    SEKHET,
    THIRD_GENDER,
    TRANS,
    TRANS_FEMALE,
    TRANS_MALE,
    TRANS_MAN,
    TRANS_PERSON,
    TRANS_WOMAN,
    TRANSGENDER,
    TRANSGENDER_FEMALE,
    TRANSGENDER_MALE,
    TRANSGENDER_MAN,
    TRANSGENDER_PERSON,
    TRANSGENDER_WOMAN,
    TRANSFEMININE,
    TRANSMASCULINE,
    TRANSSEXUAL,
    TRANSSEXUAL_FEMALE,
    TRANSSEXUAL_MALE,
    TRANSSEXUAL_MAN,
    TRANSSEXUAL_PERSON,
    TRANSSEXUAL_WOMAN,
    TRAVESTI,
    TRIGENDER,
    TUMTUM,
    TWO_SPIRIT,
    VAKASALEWALEWA,
    WARIA,
    WINKTE,
    WOMAN,
    WOMAN_OF_TRANS_EXPERIENCE,
    XENOGENDER,
    X_GENDER,

    GENDERS_SOURCE,
    GENDER_NOT_FOUND,

    ERR_NO_IMPLEMENTED,
    ERROR_USER_NOT_FOUND,
    ERROR_INVALID_EMAIL,
    ERROR_WRONG_PASSWORD,
    ERROR_FIELD_REQUIRED,
    ERROR_PASSWORDS_MISMATCH,

    LOGIN,
    SIGNUP,
    LOGOUT,
    PASSWORD,
    PASSWORD_REPEAT,
    GENDER_SELECTION,
    PROFILE,
    HEIGHT,
    WEIGHT,
    BIRTHDAY,
    GENDER,

    SUBMIT,
    SUCCESS;

    override fun toString(): String = locale.getOrCode(this)

    companion object {
        var locale: Languages = Languages.EN_US

        fun contains(value: String) = runCatching {
            Strings.valueOf(value)
            true
        }.getOrElse { false }

        fun getOrNull(value: String): String? = runCatching {
            Strings.valueOf(value).toString()
        }.getOrNull()
    }
}