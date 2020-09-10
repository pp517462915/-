# 小周新闻
## 客户端功能
### 主界面	
主界面定为显示新闻列表，采用简洁形式，主要有两部分组成，第一部分是标题栏，第二部分以新闻为载体的列表，用LinearLayout作为容器，且是垂直分布的。 可以对新闻进行增删改查操作。  
![image](https://github.com/pp517462915/news-of-xiaozhou/blob/master/image/%E6%96%B0%E9%97%BB%E9%A2%91%E9%81%93%E4%B8%BB%E8%A6%81%E7%95%8C%E9%9D%A2.png)
### 新闻频道界面
该界面用于用于显示新闻频道，并可以为其增添用户喜欢的新闻，新闻频道的数量取决于Api能给的新闻频道数量。主要有两部分组成，第一部分是标题栏，第二部分以新闻频道为载体的列表。因为新闻列表也采用了列表形式，防止用户审美疲劳，新闻频道采用卡片式布局的列表。可以对新闻频道进行增删改查操作。  
![image](https://github.com/pp517462915/news-of-xiaozhou/blob/master/image/%E6%96%B0%E9%97%BB%E5%88%97%E8%A1%A8.png)
### 搜索新闻界面
本界面用于用户通过标题模糊搜索特定的新闻。并可以对新闻进行增删改查操作。  
![image](https://github.com/pp517462915/news-of-xiaozhou/blob/master/image/%E6%96%B0%E9%97%BB%E6%90%9C%E7%B4%A2%E7%95%8C%E9%9D%A2.png)
### 新闻界面
本界面用于用户显示特定新闻的详细内容界面。  
![image](https://github.com/pp517462915/news-of-xiaozhou/blob/master/image/%E6%96%B0%E9%97%BB%E7%95%8C%E9%9D%A2.png)
## 架构设计
本系统采用的是MVVM(Model-View-ModelView)架构，Model层是Repository，Repository主要存放着云端的数据和数据库中的数据，上层而言是透明的。View层是View控制层，主要是UI的控制和显示，ViewModel层，是View层的数据部分，临时存放数据和进行数据相关的操作。
![image](https://github.com/pp517462915/news-of-xiaozhou/blob/master/image/%E6%A1%86%E6%9E%B6%E5%9B%BE.png)
