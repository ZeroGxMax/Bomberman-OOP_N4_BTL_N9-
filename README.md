<h1 align="center"><project-name>Bomberman-OOP_N4_BTL_N9-</h1>

## Author
- [Phạm Đàm Quân](https://github.com/ZeroGxMax)
- [Phạm Anh Tuấn](https://github.com/SakamakiIzayoi123)

## Built With
- Java

## Game engine
- [JavaFx](https://openjfx.io/openjfx-docs/)

## Tools build menu game
- [SceneBuilder](https://gluonhq.com/products/scene-builder/)

## Game demo
![Demo.png](Demo.png)
- Xây dựng bản đồ màn chơi từ tệp cấu hình
- ![Bomber](res/sprites/player_down.png) Di chuyển Bomber theo sự điều khiển từ người chơi.
- Tự động di chuyển các Enemy ![Balloon](res/sprites/balloom_right1.png) ![Oneal](res/sprites/oneal_right1.png) ![Doll](res/sprites/doll_right1.png) ![Kondoria](res/sprites/kondoria_right1.png) ![Minvo](res/sprites/minvo_right1.png) ![Ghost](res/sprites/ghost.png)
- Xỷ lý va chạm cho các đối tượng ![Bomber](res/sprites/player_down.png) Bomber, Enemy,
![Wall](res/sprites/wall.png) Wall, 
![Brick](res/sprites/brick.png) Brick, 
![Bomb](res/sprites/bomb.png) Bomb
- Xỷ lý bomb nổ ![](res/sprites/bomb.png)
- Xử lý Khi bomber sử dụng các Item và khi đi vào vị trí Portal
    + ![Portal](res/sprites/portal.png) Cổng dịch chuyển sang màn mới nếu diệt tất cả enemy
    + ![SpeedItem](res/sprites/powerup_speed.png) Tăng tốc bomber
    + ![FlameItem](res/sprites/powerup_flames.png) Bomb nổ dài hơn
    + ![BombItem](res/sprites/powerup_bombs.png) Đặt được nhiều bomb hơn

 ## Game advancement
![TreeExtends](Diagram.png)
- Thiết kế cây thừa kế cho các đối tượng game
- Tìm đường cho Enemy. 
- Xử lý hiệu ứng âm thanh (thêm music & sound effects)
- Cài đặt thêm các loại Enemy khác:
  + ![Oneal](res/sprites/oneal_right1.png) Oneal sẽ đuổi theo bomber nếu nằm trong phạm vi
  + ![Doll](res/sprites/doll_right1.png) Doll, chủ yếu di chuyển sang trái phải, thi thoảng sẽ đổi hướng lên xuống, chỉ thay đổi khi đụng tường
  + ![Kondoria](src/main/resources/sprites/kondoria_right1.png) Kondoria tìm kiếm bomber, có thể di chuyển qua Brick và Bomb
  + ![Ghost](src/main/resources/sprites/ghost.png) Ghost đi xuyên Brick, thi thoảng đuổi theo bomber
  + ![Minvo](src/main/resources/sprites/minvo_right1.png) di chuyển rất nhanh, di chuyển giống Doll nhưng lại đuổi theo người chơi nếu ở gần
