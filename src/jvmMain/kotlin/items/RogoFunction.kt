package items

enum class RogoFunction(
 val id: String
) {
    SHOWGROUP("Danh sách nhóm phòng"),
    ADDGROUP("Thêm nhóm phòng"),
    UPDATEGROUP("Cập nhật nhóm phòng"),
    DELETEGROUP("Xóa nhóm phòng");
    companion object {
        fun getGroupFunctions(): List<RogoFunction> {
            return listOf<RogoFunction>(
                SHOWGROUP,
                ADDGROUP,
                UPDATEGROUP,
                DELETEGROUP
            )
        }
    }
}

