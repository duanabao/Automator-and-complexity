## 课程介绍：

本课程是计算机理论课程，涉及计算模型，可计算理论，复杂性理论3个方面的知识。

### **纸上得来终觉浅，绝知此事要躬行**

1. 第2章 上下文无关的语言后可以复习下自己做的编译器。https://pandolia.net/tinyc/ch10_top_down_parse.html、
2. 图灵介绍和解读：http://www-history.mcs.st-andrews.ac.uk/Biographies/Turing.html
3. 计算模型仿真：http://www.jflap.org/
4. 。。。

## 最后的报告

### Idea

Computational complexity is the mathematical study of computational efficiency. It is concerned with identifying efficient models of computation and understanding their power, their limitations, and their interrelationships.

Besides learning about basic techniques, I wish we can talk about  how computational complexity informs what is feasible and infeasible in other information processing areas (cryptographic protocols, combinatorial optimization, "big data" computations, machine learning, game theory and economics).



### Requirements

For your final project, you can do one of the following.

- **Do a small research project.** You can either choose your own and tell me about it, or pick from the list below.

- **Study and reflect upon a work in computational complexity of your choice.** To do this kind of project, you will likely need to do some background reading. When presenting your work you are expected to place it in proper context in relation to the material in the course and give your own judgment about the advantages and disadvantages of the work. Two good places to look for possibilities are the [ECCC](http://eccc.hpi-web.de/) and [ArXiv](http://arxiv.org/list/cs.CC/recent) preprints. (A word of warning: The ArXiv papers are not refereed and the ones on ECCC are scrutinized minimally. If you find a serious error bonus points to you!)

- **Tell us about computational hardness (and how it is coped with) in your own research area.** For this type of project you will need to define your problems and computational models rigorously, justify why your definitions are sensible .

  You will need to write a report to be turned in. The length of the report is not limited.

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

| Lecture        | Topics                                                       | 讲义                       | 教材                                                    | 负责人      |
| -------------- | ------------------------------------------------------------ | -------------------------- | ------------------------------------------------------- | ----------- |
| 第1次课程      | Overview + 任务分拆 + 自动机简介                             | 第0章                      |                                                         | 老师        |
| 第2次课程      | 自动机理论（DFA，NFA，RL） + 形式语言1-正则语言（RL的运算性质，RE，） | 第1章1，2 3，4             | 中文第2章                                               | 秦雁飞      |
| 第3次课程      | 自动机理论 RE=NFA=DFA +（NRL） pump lemma                    | 第1章5，6 （举简单的例子） | 中文第2章 + 补充                                        | 方治屿      |
| 第4次课程      | 上下文无关的语言（CFL，CFG，文法标准化，讨论下歧义性）+ CFG->检查代码是否符合文法？（CFG是否能够派生出代码字符串？）+ 下推自动机的基本组成 | 第2章1，2 3，4             | 中文第3章                                               | 刘佳美      |
| 第5次课程      | CFG =下推自动机， CFL的运算性质 + NCFL与证明 + 总结          | 第2章5，6习题课            | 中文第3章                                               | 梁敏        |
| 第6次课程      | 图灵机 + 通用图灵机 + 图灵机的变形，算法鲁棒性分析           | 第3章1,2,3                 | 中文第4章                                               | 苗雪        |
| 第7次课程      | 图灵丘奇定理-可计算问题分析                                  | 第3章第4章1                | 中文第4，5章                                            | 董玲玉      |
| 第8次课程      | 不可解问题                                                   | 第4章2                     | 中文第5章                                               | 李静        |
| 第9次课程      | 归约+Rice定理1                                               | 第5章1，2                  | 中文第6章                                               | 苗俊峰      |
| 第10次课程     | 归约技巧1：历史格局+PC问题                                   | 第5章3，4                  | 中文第6章                                               | 卢旭        |
| 第11次课程     | P问题定义和分析：时间复杂度的定义；单带图灵机，多带图灵机，不确定图灵机的复杂度分析；P类问题和P类问题举例（定理8.12，13，14） | 第7章1                     | 电子中文书第8章8.1，8.2                                 | 王文喜      |
| 第12次课程     | **NP问题定义和分析**：NP类问题的定义；典型的NP类问题，P和NP（书上简单说明了，由于二者并未解决，所以可以参看其他资料，简单分析下）；多项式时间规约（定理8.26）+库克-列文定理的描述 | **第7章2**                 | 电子中文书第8章8.3，8.4.1                               | 张智敏      |
| 第13次课程     | NPC定义，NP hard定义库克-列文定理（定理8.22，8.30）与证明；总结下常见的NPC问题，分析推论8.32， | 第7章3                     | 电子中文书第8章8.4                                      | 陆海        |
| 第14次课程     | 常见的NPC问题，定义8.34，8.35，8.36，8.37                    | 第7章3                     | 电子中文书第8章8.5                                      | 徐衍睿      |
| 第15次课程     | space complexity：<br />(1) Definition 1,2 and examples 3, 4; 8.1 Savitch's theorem; 8.2 PSPACE class <br />(2) 8.3 PSPACE completeness：definition ， and 8.3.1 TQBF is PSPACE complete |                            | 中文第9章，chapter 8 in English book                    | 杜飞龙      |
| 第**16次课程** | **NSpace Complexity：<br />（1）8.3 PSPACE completeness：Winning strategies for games.  example 8.10 and theorem 8.11; Generalized Geography-theorem 8.14<br />（2）空间复杂度：总结下空间复杂度的分类，中文电子书中的定义9.11 简单回顾下证明，分析总结9.4 |                            | 中文第9章9.3后半部分+9.4，chapter 8 in English book 8.3 | 郭程皓      |
| 第17次课程     | NP近似求解：11.1，11.2                                       | 题目：                     | 中文电子书11章                                          | 张畅        |
| 第18次课程     | 高级专题：11.3，11.4，11.5                                   | 题目                       | 中文电子书11章                                          | 李毅萍+张鹏 |
| 第19次课程     | 量子理论1                                                    |                            | 补充资料                                                | 李鉴远      |
| 第20次课程     | 量子计算2                                                    | 题目：                     | 补充资料                                                | 吕征宇      |
| 第21次课程     | 因果关系1                                                    | 题目：                     | 补充资料                                                | 左鑫        |
| 第22次课程     | 因果关系2                                                    | 题目                       | 补充资料                                                | 修昊+王小芬 |
| 第23次课程     | 报告 (在做自己的研究的同时，整理整理final report)            | 题目                       |                                                         |             |
| 第24次课程     | 报告 (在做自己的研究的同时，整理整理final report)            | 题目                       |                                                         |             |

