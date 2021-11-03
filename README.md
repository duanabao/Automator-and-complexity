## c课程介绍：

本课程是计算机理论课程，讲述计算机的基本功能和局限性，形式化描述计算机硬件和软件的数学特性；主要内容包括3个方面的知识：

- 计算模型：主要总结两个计算模型FA和PDA的基本功能和局限性
- 可计算理论：主要总结图灵机模型，并熟悉如何判定计算机的基本计算能力，以及什么样的问题是计算机不可计算的问题。
- 复杂性理论：这个是本学期的重点，如果确认要解决问题的复杂性，以及如何为复杂问题给出近似解

另外本课程在上述计算模型以及分析计算能力：能判定、识别的形式语言的基础之上，进行了理论和应用的扩展

- 理论扩展：通过读取论文，理解量子计算机是否计算能力 > 图灵机模型

- 应用扩展：通过读取论文，基于图计算，理解问题的形式化、问题求解，求解的可解释性。

  

  本课程课程目标如下：

  1. 计算机理论：希望学习如何思考、证明、论证、解决问题、表达和抽象。
  2. 理论与实践结合：学习到解决问题的技巧，涉及到的实践包括：新的编程语言设计、编译器、字符串搜索、模式匹配、计算机安全、人工智能等方面。 

  

### **纸上得来终觉浅，绝知此事要躬行**

1. 第2章 上下文无关的语言后可以复习下自己做的编译器。https://pandolia.net/tinyc/ch10_top_down_parse.html、
2. 图灵介绍和解读：http://www-history.mcs.st-andrews.ac.uk/Biographies/Turing.html
3. 计算模型仿真：http://www.jflap.org/
4. 。。。。。



## The key point in the course： Computational complexity

### Idea

Computational complexity is the mathematical study of computational efficiency. It is concerned with identifying efficient models of computation and understanding their power, their limitations, and their interrelationships.

Besides learning about basic techniques, I wish we can talk about  how computational complexity informs what is feasible and infeasible in other information processing areas (cryptographic protocols, combinatorial optimization, "big data" computations, machine learning, game theory and economics).



### Requirements

For your final project, you can do one of the following.

- **Do a small research project.** You can master how to describe the complexity, and prove the complexity class of  the problem related with your own research interest.

- **Study and reflect upon a work in computational complexity of your choice.** we will likely need to do some background reading. When presenting your work you are expected to place it in proper context in relation to the material in the course and give your own judgment about the advantages and disadvantages of the work. Two good places to look for possibilities are the [ECCC](http://eccc.hpi-web.de/) and [ArXiv](http://arxiv.org/list/cs.CC/recent) preprints. (A word of warning: The ArXiv papers are not refereed and the ones on ECCC are scrutinized minimally. If you find a serious error bonus points to you!)

- **Tell us about computational hardness (and how it is coped with) in your own research area.** we will try to define the problems and computational models rigorously, justify why your definitions are sensible .

  

  ### **课程分数**

  1. 计算理论书籍阅读和讲解： 60分

  2. 扩展论文阅读和讲解：40分
  
     
  
  ### Research project possibilities
  
  - #### Models
  
  - | 1    | Decision tree              |
    | ---- | -------------------------- |
    | 2    | Parallel computation       |
    | 3    | Sequential computation     |
    | 4    | Sublinear-time computation |
    | 5    | Quantum computation        |

  - #### Application

  - | 1    | Big Data         |
    | ---- | ---------------- |
    | 2    | Machine learning |

  - #### Monotone circuit lower bounds

  - In the monotone circuit lower bound for CLIQUE we showed that no small monotone circuit can simultaneously accept all positive instances and reject all negative instances. However there is a simple monotone circuit that accepts all negative instances and rejects all positive ones (what is it?) Can you come up with a possibly different monotone function so that no small monotone circuit can completely distinguish positive or negative in either direction?
  
  - #### Average-case complexity of approximate counting and sampling
  
  - Extend the theory of average-case complexity from search and decision to approximate counting and approximate sampling problems. I suggest you start with some examples of such problems that are easy and hard on average, state definitions, and try to relate these two types of problems (they are known to be worst-case equivalent in certain settings, e.g. approximately counting and sampling perfect matchings in graphs.)
  
  - #### 
  
  - 

## 课程内容安排：

| Lecture       | Topics                                                       | 讲义                       | 教材                                                    | 负责人         | 讨论题目 |
| ------------- | ------------------------------------------------------------ | -------------------------- | ------------------------------------------------------- | -------------- | -------- |
| 第1次课程     | Overview + 任务分拆 + 复杂性定义+extension （quantum，Interpretability） |                            |                                                         | 老师           |          |
| 第2次课程     | 自动机理论（DFA，NFA） + 形式语言1-正则语言（RL的运算性质:闭包，RE） | 第1章1，2 3，4             | 中文第2章<br />英文:§1.1-§1.3                           | 宋昆           | 问题1    |
|               | 自动机理论 RE=NFA=DFA +（NRL） pump lemma                    | 第1章5，6 （举简单的例子） | 中文第2章 + 补充<br />英文:§1.3-§1.4                    | 吉锋瑞         |          |
| 第3次课程     | 上下文无关的语言（CFL，CFG，文法标准化，讨论下歧义性）+ CFG->检查代码是否符合文法？（CFG是否能够派生出代码字符串？）+ 下推自动机的基本组成 | 第2章1，2 3，4             | 中文第3章                                               | 刘孟寅         |          |
|               | CFG =下推自动机， CFL的运算性质 + NCFL与证明 + 总结          | 第2章5，6习题课            | 中文第3章                                               | 庄雨           |          |
| 第4次课程     | 图灵机 + 通用图灵机 + 图灵机的变形，算法鲁棒性分析           | 第3章1,2,3                 | 中文第4章                                               | 朱康希         |          |
|               | 图灵丘奇定理                                                 | 第3章                      | 中文第4章                                               | 刘雪松         |          |
| 第5次课程     | 可计算问题分析                                               | 第4章1                     | 中文第5章                                               | 李长泰         |          |
|               | 不可解问题                                                   | 第4章2                     | 中文第5章                                               | 任世奇         |          |
| 第6次课程     | 归约+Rice定理1                                               | 第5章1，2                  | 中文第6章                                               | 肖楠           |          |
|               | 归约技巧1：历史格局+PC问题                                   | 第5章3，4                  | 中文第6章                                               | 国贤玉         |          |
| 第7次课程     | P问题定义和分析：时间复杂度的定义；单带图灵机，多带图灵机，不确定图灵机的复杂度分析；P类问题和P类问题举例（定理8.12，13，14） | 第7章1                     | 电子中文书第8章8.1，8.2                                 | 杨晓芬         |          |
|               | **NP问题定义和分析**：NP类问题的定义；典型的NP类问题，P和NP（书上简单说明了，由于二者并未解决，所以可以参看其他资料，简单分析下）；多项式时间规约（定理8.26）+库克-列文定理的描述 | **第7章2**                 | 电子中文书第8章8.3，8.4.1                               | 师亚勇         |          |
| 第8次课程     | NPC定义，NP hard定义库克-列文定理（定理8.22，8.30）与证明；总结下常见的NPC问题，分析推论8.32， | 第7章3                     | 电子中文书第8章8.4                                      | 李荣洋         |          |
|               | 常见的NPC问题，定义8.34，8.35，8.36，8.37                    | 第7章3                     | 电子中文书第8章8.5                                      | 杜泉成，国峰   |          |
| 第**9次课程** | space complexity：<br />(1) Definition 1,2 and examples 3, 4; 8.1 Savitch's theorem; 8.2 PSPACE class <br />(2) 8.3 PSPACE completeness：definition ， and 8.3.1 TQBF is PSPACE complete |                            | 中文第9章，chapter 8 in English book                    | 万菲           |          |
|               | **NSpace Complexity：<br />（1）8.3 PSPACE completeness：Winning strategies for games.  example 8.10 and theorem 8.11; Generalized Geography-theorem 8.14，L and NL<br />（2）空间复杂度：总结下空间复杂度的分类，中文电子书中的定义9.11 简单回顾下证明，分析总结9.4 |                            | 中文第9章9.3后半部分+9.4，chapter 8 in English book 8.3 | 赵秀锋；张世学 |          |
| 第10次课程    | NL-completeness, NL=coNL, Hierarchy theorems                 |                            | 中文电子书10章                                          | 陈龙           |          |
|               | Provably intractable problems, oracles                       |                            | 中文电子书10章                                          | 马启程         |          |
| 第11次课程    | NP近似求解：11.1，概率算法(Probabilistic computation, BPP):11.2 |                            | 中文电子书11章                                          | 杨文金         |          |
|               | 概率算法， 高级专题：11.3                                    |                            | 中文电子书11章                                          | 韩铮           |          |
| 第12次课程    | 高级专题：11.3，11.4                                         |                            | 中文电子书11章                                          | 熊琛           |          |
|               | 高级专题：11.5: coNP ⊆ IP ，总结时间复杂度和空间复杂度       |                            | 中文电子书11章                                          | 王稼祥         |          |
| 第13次课程    | 论文总结                                                     |                            | 补充资料                                                |                |          |
| 第14次课程    | 论文总结                                                     |                            | 补充资料                                                |                |          |
| 第15次课程    | 论文总结                                                     |                            | 补充资料                                                |                |          |
| 第16次课程    | 论文总结                                                     |                            | 补充资料                                                |                |          |

