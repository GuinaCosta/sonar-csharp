/*
 * Copyright (C) 2010 SonarSource SA
 * All rights reserved
 * mailto:contact AT sonarsource DOT com
 */
package com.sonar.csharp.parser.rules.expressions;

import static com.sonar.sslr.test.parser.ParserMatchers.parse;
import static org.junit.Assert.assertThat;

import org.junit.Before;
import org.junit.Test;

import com.sonar.csharp.api.CSharpGrammar;
import com.sonar.csharp.parser.CSharpParser;

public class ElementAccessTest {

  CSharpParser p = new CSharpParser();
  CSharpGrammar g = p.getGrammar();

  @Before
  public void init() {
    p.setRootRule(g.elementAccess);
  }

  @Test
  public void testOk() {
    g.primaryNoArrayCreationExpression.mock();
    g.expressionList.mock();
    assertThat(p, parse("primaryNoArrayCreationExpression[expressionList]"));
    assertThat(p, parse("primaryNoArrayCreationExpression [ expressionList ]"));
  }

  @Test
  public void testRealLife() throws Exception {
    assertThat(p, parse("GetProperties(true)[key]"));
  }
  
}
